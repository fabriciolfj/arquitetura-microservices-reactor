package com.github.fabriciolfj.product.domain.port.in;

import com.github.fabriciolfj.product.domain.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductIn {

    Mono<Product> findByCode(final String id);
    Flux<Product> getAll();
    Mono<?> create(final Product product);
}
