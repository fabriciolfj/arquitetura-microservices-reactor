package com.github.fabriciolfj.product.adapters.facade;

import com.github.fabriciolfj.product.domain.model.ProductOperationModel;
import com.github.fabriciolfj.product.domain.port.in.OperationIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OperationCreate {

    private final OperationIn operationIn;

    public Mono<Void> addModel(final String code, final Integer qtde) {
        return Mono.just(ProductOperationModel.toMapper(code, qtde))
                .flatMap(p -> {
            log.info("Model created: " + p.toString());
            return operationIn.addInventory(p);
        });

    }

    public Mono<Void> exitModel(final String code, final Integer qtde) {
        return Mono.just(ProductOperationModel.toMapper(code, qtde))
                .flatMap(p -> {
                    log.info("Model created: " + p.toString());
                    return operationIn.exitInventory(p);
                });
    }
}
