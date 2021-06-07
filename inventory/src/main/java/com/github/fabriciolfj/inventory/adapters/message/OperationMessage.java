package com.github.fabriciolfj.inventory.adapters.message;

import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgRequest;
import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgResponse;
import com.github.fabriciolfj.inventory.application.mappers.OperacaoInventarioModelMapper;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import com.github.fabriciolfj.inventory.core.port.in.InventarioIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


import java.util.function.Function;

import static java.util.Optional.of;

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
        final var model = modelMapper.toModel(request);
        return of(model)
                .filter(m -> m.getQtdeEntrada() > 0)
                .map(this::processAdd)
                .orElseGet(() ->  processExit(model));
    }

    private OperationMsgResponse processExit(final OperacaoInventarioModel model) {
        return in.saidaInventario(model)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(e -> log.info("processo success {}", e))
                .doOnError(e -> log.error("processor error {}", e.getMessage()))
                .map(result -> modelMapper.toResponse(result))
                .onErrorResume(e -> Mono.just(OperationMsgResponse.builder()
                        .balance(-1)
                        .code(model.getCode())
                        .build()))
                .block();
    }

    private OperationMsgResponse processAdd(final OperacaoInventarioModel model) {
        return in.addInventario(model)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(e -> log.info("processo success {}", e))
                .doOnError(e -> log.error("processor error {}", e.getMessage()))
                .map(result -> modelMapper.toResponse(result))
                .block();
    }
}
