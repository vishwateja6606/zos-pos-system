package com.zosh.zosh.pos.system.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zosh.zosh.pos.system.exceptions.CategoryNotFoundException;
import com.zosh.zosh.pos.system.mapper.CategoryMapper;
import com.zosh.zosh.pos.system.mapper.ProductMapper;
import com.zosh.zosh.pos.system.modal.Category;
import com.zosh.zosh.pos.system.payload.dto.CategoryDto;
import com.zosh.zosh.pos.system.payload.response.CategoryResponseDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;
import com.zosh.zosh.pos.system.repository.CategoryRepository;
import com.zosh.zosh.pos.system.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryDto dto) {

        Category category = new Category();
        category.setName(dto.getName());
        

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + id));

        return CategoryMapper.toResponse(category);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + categoryId));

        return category.getProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

	@Override
	public List<ProductResponseDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}
}
