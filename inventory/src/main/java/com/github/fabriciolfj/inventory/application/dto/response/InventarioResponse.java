package com.github.fabriciolfj.inventory.application.dto.response;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioResponse {

    private String code;
    private Integer saldo;

    public static InventarioResponse toMapper(final Extrato extrato) {
        return InventarioResponse.builder()
                .code(extrato.getCode())
                .saldo(extrato.getSaldo())
                .build();
    }
}
