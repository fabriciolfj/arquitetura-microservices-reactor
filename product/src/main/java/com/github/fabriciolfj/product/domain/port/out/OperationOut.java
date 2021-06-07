package com.github.fabriciolfj.product.domain.port.out;

import com.github.fabriciolfj.product.domain.model.OperationType;
import reactor.core.publisher.Mono;

public interface OperationOut {

    Mono<Void> send(final String code, final Integer qtde, final OperationType type);
}
