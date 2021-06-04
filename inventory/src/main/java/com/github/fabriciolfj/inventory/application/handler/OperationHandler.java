package com.github.fabriciolfj.inventory.application.handler;

import com.github.fabriciolfj.inventory.application.dto.request.InventarioEntradaRequest;
import com.github.fabriciolfj.inventory.application.dto.request.InventarioSaidaRequest;
import com.github.fabriciolfj.inventory.application.mappers.OperacaoInventarioModelMapper;
import com.github.fabriciolfj.inventory.core.exceptions.Error;
import com.github.fabriciolfj.inventory.core.port.in.InventarioIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OperationHandler {

    private final InventarioIn inventarioIn;
    private final OperacaoInventarioModelMapper modelMapper;

    public Mono<ServerResponse> exit(final ServerRequest request) {
        return request.bodyToMono(InventarioSaidaRequest.class)
                .map(modelMapper::toModel)
                .flatMap(inventarioIn::saidaInventario)
                .flatMap(extrato -> ServerResponse.ok().bodyValue(extrato))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(Error
                        .builder()
                        .dateTime(LocalDateTime.now())
                        .message(e.getMessage())
                        .build()));
    }

    public Mono<ServerResponse> add(final ServerRequest request) {
        return request.bodyToMono(InventarioEntradaRequest.class)
                .map(modelMapper::toModel)
                .flatMap(inventarioIn::addInventario)
                .flatMap(inv -> ServerResponse.ok().bodyValue(inv))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(Error.builder()
                        .message(e.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()));
    }
}
