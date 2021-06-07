package com.github.fabriciolfj.product.domain.port.in;

import com.github.fabriciolfj.product.domain.model.ProductOperationModel;
import reactor.core.publisher.Mono;

public interface OperationIn {

    Mono<Void> addInventory(final ProductOperationModel model);
    Mono<Void> exitInventory(final ProductOperationModel model);

}
