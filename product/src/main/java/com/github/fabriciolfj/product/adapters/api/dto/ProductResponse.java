package com.github.fabriciolfj.product.adapters.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private String id;
    private String code;
    private Integer qtde;
}
