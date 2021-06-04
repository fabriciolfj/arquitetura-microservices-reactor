package com.github.fabriciolfj.inventory.core.facade.mapper.impl;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.facade.mapper.ExtratoMapper;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@NoArgsConstructor
public abstract class ExtratoMapperDecorated implements ExtratoMapper {

    @Autowired
    private ExtratoMapper extratoMapper;

    @Override
    public Extrato toDocument(final OperacaoInventarioModel model) {
        var extrato = extratoMapper.toDocument(model);
        extrato.setMov(LocalDateTime.now());
        extrato.setSaida(0);
        return extrato;
    }
}
