package com.github.fabriciolfj.inventory.application.mappers;

import com.github.fabriciolfj.inventory.application.dto.response.InventarioResponse;
import com.github.fabriciolfj.inventory.core.documents.Extrato;
import org.springframework.stereotype.Component;

@Component
public class InventarioResponseMapper {

    public InventarioResponse toResponse(final Extrato extrato) {
        return InventarioResponse.toMapper(extrato);
    }
}
