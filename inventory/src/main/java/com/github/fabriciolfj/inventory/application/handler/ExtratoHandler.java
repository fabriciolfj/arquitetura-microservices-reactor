package com.github.fabriciolfj.inventory.application.handler;

import com.github.fabriciolfj.inventory.application.dto.response.InventarioResponse;
import com.github.fabriciolfj.inventory.application.mappers.InventarioResponseMapper;
import com.github.fabriciolfj.inventory.core.exceptions.Error;
import com.github.fabriciolfj.inventory.core.port.out.InventarioOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ExtratoHandler {

    private final InventarioOut inventarioOut;
    private final InventarioResponseMapper responseMapper;

    public Mono<ServerResponse> get(final ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(inventarioOut.getInventarios(), InventarioResponse.class);
    }

    public Mono<ServerResponse> findByCode(final ServerRequest request) {
        final var code = request.pathVariable("code");
        return ServerResponse.ok().body(inventarioOut.findByInventario(code)
                                        .map(responseMapper::toResponse), InventarioResponse.class)
                .onErrorResume(e -> ServerResponse
                        .badRequest().bodyValue(Error.builder()
                                .message(e.getMessage())
                                .dateTime(LocalDateTime.now())
                                .build()));
    }
}
