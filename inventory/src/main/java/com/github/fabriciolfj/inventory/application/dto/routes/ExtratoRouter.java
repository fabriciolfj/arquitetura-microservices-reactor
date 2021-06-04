package com.github.fabriciolfj.inventory.application.dto.routes;

import com.github.fabriciolfj.inventory.application.handler.ExtratoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class ExtratoRouter {

    private final ExtratoHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/extratos/{code}").and(accept(APPLICATION_JSON)), handler::findByCode)
                .andRoute(GET("/extratos").and(accept(APPLICATION_JSON)), handler::get);
    }
}
