package com.zosh.zosh.pos.system.mapper;

import java.util.List;

import com.zosh.zosh.pos.system.modal.Category;
import com.zosh.zosh.pos.system.payload.response.CategoryResponseDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;

public class CategoryMapper {

    public static CategoryResponseDto toResponse(Category category) {

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
  

        List<ProductResponseDto> products =
                category.getProducts()
                        .stream()
                        .map(ProductMapper::toResponse)
                        .toList();

        dto.setProducts(products);
        return dto;
    }
}

