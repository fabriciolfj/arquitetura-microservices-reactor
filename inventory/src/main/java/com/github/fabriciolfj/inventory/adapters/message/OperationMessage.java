package com.github.fabriciolfj.inventory.adapters.message;

import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgRequest;
import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgResponse;
import com.github.fabriciolfj.inventory.application.mappers.OperacaoInventarioModelMapper;
import com.github.fabriciolfj.inventory.core.port.in.InventarioIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
@Slf4j
public class OperationMessage {

    private final OperacaoInventarioModelMapper modelMapper;
    private final InventarioIn in;

    @Bean
    public Function<OperationMsgRequest, OperationMsgResponse> operation() {
        return value -> process(value);
    }

    private OperationMsgResponse process(final OperationMsgRequest request) {
        log.info("Add {}", request.toString());
        var extrato = in.addInventario(modelMapper.toModel(request))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(e -> log.info("processo success {}", e))
                .doOnError(e -> log.error("processor error {}", e.getMessage()))
                .block();

        return modelMapper.toResponse(extrato);
    }
}
