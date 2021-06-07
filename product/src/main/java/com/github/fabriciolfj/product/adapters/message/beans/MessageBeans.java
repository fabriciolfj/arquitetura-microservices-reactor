package com.github.fabriciolfj.product.adapters.message.beans;

import com.github.fabriciolfj.product.adapters.message.dto.OperationInventarioResponse;
import com.github.fabriciolfj.product.domain.document.Product;
import com.github.fabriciolfj.product.domain.port.in.ProductIn;
import com.github.fabriciolfj.product.domain.service.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageBeans {

    private final ProductIn productIn;

    @Bean
    public Consumer<OperationInventarioResponse> inventory() {
      return value -> {
          log.info(value.toString());
          productIn.findByCode(value.getCode())
                  .flatMap(p -> {
                      p.setBalance(value.getBalance());
                      return productIn.create(p);
                  }).subscribe(prod -> log.info("Product update: {}", prod));


      };
    }
}
