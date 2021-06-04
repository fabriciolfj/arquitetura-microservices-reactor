package com.github.fabriciolfj.inventory.application.dto.routes;

import com.github.fabriciolfj.inventory.application.handler.OperationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class OperationRouter {

    private final OperationHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(POST("/operation/add").and(accept(APPLICATION_JSON)), handler::add)
                .andRoute(POST("/operation/exit").and(accept(APPLICATION_JSON)), handler::exit);
    }
}
