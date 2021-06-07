package com.github.fabriciolfj.inventory.application.mappers.impl;

import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgRequest;
import com.github.fabriciolfj.inventory.adapters.message.dto.OperationType;
import com.github.fabriciolfj.inventory.application.mappers.OperacaoInventarioModelMapper;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class OperationInventarioDecorate implements OperacaoInventarioModelMapper {

    @Autowired
    private OperacaoInventarioModelMapper modelMapper;

    @Override
    public OperacaoInventarioModel toModel(OperationMsgRequest request) {
        var operation = modelMapper.toModel(request);
        var type = OperationType.toEnum(request.getType());

        if (type.equals(OperationType.ENTRANCE)) {
            operation.setQtdeEntrada(request.getQtde());
            return operation;
        }

        operation.setQtdeSaida(request.getQtde());
        return operation;
    }
}
