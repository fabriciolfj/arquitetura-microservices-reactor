package com.github.fabriciolfj.inventory.core.port.in;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import reactor.core.publisher.Mono;

public interface InventarioIn {

    Mono<Extrato> addInventario(final OperacaoInventarioModel model);
    Mono<Extrato> saidaInventario(final OperacaoInventarioModel model);
}
