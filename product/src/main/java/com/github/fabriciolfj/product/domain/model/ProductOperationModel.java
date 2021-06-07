package com.github.fabriciolfj.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOperationModel {

    private String code;
    private Integer qtde;

    public static ProductOperationModel toMapper(final String code, final Integer qtde) {
        return ProductOperationModel.builder()
                .qtde(qtde)
                .code(code)
                .build();
    }
}
