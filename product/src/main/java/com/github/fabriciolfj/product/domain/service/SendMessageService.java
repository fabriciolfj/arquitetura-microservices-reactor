package com.github.fabriciolfj.product.domain.service;

import com.github.fabriciolfj.product.domain.exceptions.OperationException;
import com.github.fabriciolfj.product.domain.model.OperationType;
import com.github.fabriciolfj.product.domain.model.ProductOperationModel;
import com.github.fabriciolfj.product.domain.port.out.OperationOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMessageService {

    private final ProductService productService;
    private final OperationOut operationOut;

    public Mono<Void> invokeStream(final ProductOperationModel model, final OperationType type) {
        return productService.findByCode(model.getCode())
                .flatMap(p -> {
                    log.info("Product found: {}", p.toString());
                    return operationOut.send(p.getCode(), model.getQtde(), type);
                })
                .onErrorResume(e->
                        Mono.defer(() ->
                                Mono.error(new OperationException("Operation failed. Details: " + e.getMessage()))
                        )
                ).log();
    }
}
