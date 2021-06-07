package com.github.fabriciolfj.product.adapters.message.producer;

import com.github.fabriciolfj.product.adapters.message.dto.OperationInventarioRequest;
import com.github.fabriciolfj.product.domain.model.OperationType;
import com.github.fabriciolfj.product.domain.port.out.OperationOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OperationProducer implements OperationOut {

    private final StreamBridge streamBridge;

    @Override
    public Mono<Void> send(final String code, final Integer qtde, final OperationType type) {
        log.info("Send msg: {} {} {}", code, qtde, type.getDescription());
        return Mono.justOrEmpty(OperationInventarioRequest.toRequest(code, qtde, type))
                .flatMap(request -> {
                    streamBridge.send("operation-group", request);
                    return Mono.empty();
                });
    }
}
