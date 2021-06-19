package com.github.fabriciolfj.product.adapters.api.controller;

import com.github.fabriciolfj.product.adapters.api.dto.ProductRequest;
import com.github.fabriciolfj.product.adapters.api.dto.ProductResponse;
import com.github.fabriciolfj.product.adapters.api.mappers.ProductMapper;
import com.github.fabriciolfj.product.domain.document.Product;
import com.github.fabriciolfj.product.domain.port.in.ProductIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductIn productOut;
    private final ProductMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<ProductResponse> getAll() {
        return productOut.getAll()
                .map(p -> mapper.toResponse(p));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> create(@RequestBody final ProductRequest request) {
        return productOut.create(mapper.toDocument(request))
                .flatMap(doc -> Mono.just(mapper.toResponse((Product) doc)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ProductResponse> findByCode(@PathVariable("id") final String code) {
        return productOut.findByCode(code)
                .flatMap(doc -> Mono.just(mapper.toResponse(doc)));
    }
}
