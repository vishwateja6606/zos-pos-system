package com.zosh.zosh.pos.system.service;

import java.util.List;

import com.zosh.zosh.pos.system.payload.dto.ProductDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(ProductDto dto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();
}
