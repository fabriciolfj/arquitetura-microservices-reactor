package com.github.fabriciolfj.inventory.core.service;

import com.github.fabriciolfj.inventory.adapters.repository.ExtratoRepository;
import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.facade.mapper.ExtratoMapper;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import com.github.fabriciolfj.inventory.core.port.in.InventarioIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OperationService implements InventarioIn {

    private final ExtratoRepository extratoRepository;
    private final ExtratoMapper extratoMapper;

    @Override
    public Mono<Extrato> addInventario(final OperacaoInventarioModel model) {
        return extratoRepository.findFirstByCodeOrderByMovDesc(model.getCode())
                .flatMap(e -> {
                    var extrato = Extrato.add(model.getQtdeEntrada(), e);
                    return extratoRepository.save(extrato);
                }).switchIfEmpty(extratoRepository.save(extratoMapper.toDocument(model)));
    }

    @Override
    public Mono<Extrato> saidaInventario(final OperacaoInventarioModel model) {
        return extratoRepository.findFirstByCodeOrderByMovDesc(model.getCode())
                .map(e -> {
                    if (e.getSaldo() < model.getQtdeSaida()) {
                        throw new RuntimeException("Stock insufficient. Stock: " + model.getQtdeSaida() + ", operation: " + e.getSaldo());
                    }

                    return Extrato.exit(model.getQtdeSaida(), e);
                })
                .flatMap(e -> extratoRepository.save(e))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Extrato not found: " + model.getCode()))));
    }
}
