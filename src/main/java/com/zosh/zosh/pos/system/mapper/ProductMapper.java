package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.modal.Product;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;

public class ProductMapper {

    public static ProductResponseDto toResponse(Product product) {

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setStatus(product.getStatus());
        return dto;
    }
}
