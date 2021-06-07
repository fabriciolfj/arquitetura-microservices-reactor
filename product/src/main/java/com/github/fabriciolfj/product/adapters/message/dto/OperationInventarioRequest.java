package com.github.fabriciolfj.product.adapters.message.dto;

import com.github.fabriciolfj.product.domain.model.OperationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationInventarioRequest {

    private String code;
    private Integer qtde;
    private String type;

    public static OperationInventarioRequest toRequest(final String code, final Integer qtde, final OperationType type) {
        return OperationInventarioRequest
                .builder()
                .code(code)
                .qtde(qtde)
                .type(type.getDescription())
                .build();
    }


}
