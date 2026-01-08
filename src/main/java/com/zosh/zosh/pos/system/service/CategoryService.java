package com.zosh.zosh.pos.system.service;

import java.util.List;

import com.zosh.zosh.pos.system.payload.dto.CategoryDto;
import com.zosh.zosh.pos.system.payload.response.CategoryResponseDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;

public interface CategoryService {
	CategoryResponseDto createCategory(CategoryDto dto);
	CategoryResponseDto getCategoryById(Long id);
	List<ProductResponseDto> getProductsByCategory(Long categoryId);
	   List<ProductResponseDto> getAllProducts();

}
