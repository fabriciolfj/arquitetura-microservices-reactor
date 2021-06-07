package com.github.fabriciolfj.product.domain.service;

import com.github.fabriciolfj.product.domain.document.Product;
import com.github.fabriciolfj.product.domain.exceptions.ProductNotFoundException;
import com.github.fabriciolfj.product.domain.port.in.ProductIn;
import com.github.fabriciolfj.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductIn {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> findByCode(final String id) {
        return productRepository.findByCode(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ProductNotFoundException("Product not found: " + id))))
                .log();
    }

    @Override
    public Flux<Product> getAll() {
        return productRepository.findAll()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Product> create(final Product product) {
        return productRepository.save(product);
    }
}
