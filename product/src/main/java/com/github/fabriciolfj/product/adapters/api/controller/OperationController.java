package com.github.fabriciolfj.product.adapters.api.controller;

import com.github.fabriciolfj.product.adapters.facade.OperationCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationCreate operationCreate;

    @PutMapping("/add/{code}/{qtde}")
    public Mono<Void> addInventory(@PathVariable("code") final String code, @PathVariable("qtde") final Integer qtde) {
        return operationCreate.addModel(code, qtde);
    }

    @PutMapping("/exit/{code}/{qtde}")
    public Mono<Void> exitInventory(@PathVariable("code") final String code, @PathVariable("qtde") final Integer qtde) {
        return operationCreate.exitModel(code, qtde);
    }
}
