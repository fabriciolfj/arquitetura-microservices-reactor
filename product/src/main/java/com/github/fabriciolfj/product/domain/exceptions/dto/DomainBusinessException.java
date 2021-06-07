package com.github.fabriciolfj.product.domain.exceptions.dto;

public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(final String msg) {
        super(msg);
    }
}
