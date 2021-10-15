package com.github.fabriciolfj.inventory.core.service;

import com.github.fabriciolfj.inventory.adapters.repository.ExtratoRepository;
import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.port.out.InventarioOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ExtratoService implements InventarioOut {

    private final ExtratoRepository extratoRepository;

    @Override
    public Flux<Extrato> getInventarios() {
        return extratoRepository.findAll()
                .onBackpressureBuffer(100)
                .publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Extrato> findByInventario(final String code) {
        return extratoRepository.findFirstByCodeOrderByMovDesc(code);
    }
}
