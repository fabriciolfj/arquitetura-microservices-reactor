package com.github.fabriciolfj.product.adapters.api.mappers;

import com.github.fabriciolfj.product.adapters.api.dto.ProductRequest;
import com.github.fabriciolfj.product.adapters.api.dto.ProductResponse;
import com.github.fabriciolfj.product.domain.document.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "qtde", source = "balance")
    ProductResponse toResponse(final Product product);

    @Mapping(target = "id", ignore = true)
    Product toDocument(final ProductRequest request);
}
