package com.github.fabriciolfj.inventory.application.mappers;

import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgRequest;
import com.github.fabriciolfj.inventory.adapters.message.dto.OperationMsgResponse;
import com.github.fabriciolfj.inventory.application.dto.request.InventarioEntradaRequest;
import com.github.fabriciolfj.inventory.application.dto.request.InventarioSaidaRequest;
import com.github.fabriciolfj.inventory.application.mappers.impl.OperationInventarioDecorate;
import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(OperationInventarioDecorate.class)
public interface OperacaoInventarioModelMapper {

    @Mapping(source = "code", target = "code")
    @Mapping(source = "qtde", target = "qtdeEntrada")
    @Mapping(target = "qtdeSaida", ignore = true)
    OperacaoInventarioModel toModel(final InventarioEntradaRequest request);

    @Mapping(source = "code", target = "code")
    @Mapping(source = "qtde", target = "qtdeSaida")
    @Mapping(target = "qtdeEntrada", ignore = true)
    OperacaoInventarioModel toModel(final InventarioSaidaRequest request);

    @Mapping(source = "code", target = "code")
    @Mapping(target = "qtdeSaida", ignore = true)
    @Mapping(target = "qtdeEntrada", ignore = true)
    OperacaoInventarioModel toModel(final OperationMsgRequest request);

    @Mapping(source = "code", target = "code")
    @Mapping(source = "saldo", target = "balance")
    OperationMsgResponse toResponse(final Extrato extrato);
}
