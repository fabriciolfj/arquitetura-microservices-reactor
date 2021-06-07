package com.github.fabriciolfj.product.adapters.message.beans;

import com.github.fabriciolfj.product.adapters.message.dto.OperationInventarioResponse;
import com.github.fabriciolfj.product.domain.port.in.ProductIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageBeans {

    private final ProductIn productIn;

    @Bean
    public Consumer<OperationInventarioResponse> inventory() {
      return value -> {
          Mono.just(value)
                  .filter(v -> v.getBalance() > 0)
                  .flatMap(p -> productIn.findByCode(value.getCode()))
                  .flatMap(prod -> {
                      prod.setBalance(value.getBalance());
                      return productIn.create(prod);
                  }).subscribe(prod -> log.info("Product update: {}", prod));
      };
    }
}
