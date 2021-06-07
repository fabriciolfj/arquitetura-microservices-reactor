package com.github.fabriciolfj.product.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String msg) {
        super(msg);
    }
}
