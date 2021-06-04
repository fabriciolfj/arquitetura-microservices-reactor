package com.github.fabriciolfj.inventory.core.port.out;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventarioOut {

    Flux<Extrato> getInventarios();
    Mono<Extrato> findByInventario(final String code);
}
