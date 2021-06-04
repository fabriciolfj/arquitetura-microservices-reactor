package com.github.fabriciolfj.inventory.core.facade.mapper;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import com.github.fabriciolfj.inventory.core.facade.mapper.impl.ExtratoMapperDecorated;
import com.github.fabriciolfj.inventory.core.model.OperacaoInventarioModel;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(ExtratoMapperDecorated.class)
public interface ExtratoMapper {

    @Mapping(source = "code", target = "code")
    @Mapping(source = "qtdeEntrada", target = "entrada")
    @Mapping(source = "qtdeSaida", target = "saida")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saldo", source = "qtdeEntrada")
    @Mapping(target = "mov", ignore = true)
    Extrato toDocument(final OperacaoInventarioModel model);
}
