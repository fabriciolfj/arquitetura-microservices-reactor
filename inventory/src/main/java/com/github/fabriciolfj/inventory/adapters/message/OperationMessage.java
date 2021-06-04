package com.github.fabriciolfj.inventory.adapters.message;

import com.github.fabriciolfj.inventory.application.dto.request.InventarioEntradaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class OperationMessage {

    @Bean
    public Consumer<InventarioEntradaRequest> entrada() {
        return value -> {
            process(value);
        };
    }

    private void process(final InventarioEntradaRequest request) {
        log.info(request.toString());
    }
}
