package com.github.fabriciolfj.product.domain.service;

import com.github.fabriciolfj.product.domain.exceptions.OperationException;
import com.github.fabriciolfj.product.domain.model.OperationType;
import com.github.fabriciolfj.product.domain.model.ProductOperationModel;
import com.github.fabriciolfj.product.domain.port.in.OperationIn;
import com.github.fabriciolfj.product.domain.port.out.OperationOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OperationService implements OperationIn {

    private final SendMessageService sendMessageService;

    @Override
    public Mono<Void> addInventory(final ProductOperationModel model) {
        return sendMessageService.invokeStream(model, OperationType.ENTRANCE);
    }

    @Override
    public Mono<Void> exitInventory(final ProductOperationModel model) {
        return sendMessageService.invokeStream(model, OperationType.EXIT);
    }
}
