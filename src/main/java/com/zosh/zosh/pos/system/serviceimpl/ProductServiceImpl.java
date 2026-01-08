package com.zosh.zosh.pos.system.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zosh.zosh.pos.system.exceptions.CategoryNotFoundException;
import com.zosh.zosh.pos.system.exceptions.ProductNotFoundException;
import com.zosh.zosh.pos.system.mapper.ProductMapper;
import com.zosh.zosh.pos.system.modal.Category;
import com.zosh.zosh.pos.system.modal.Product;
import com.zosh.zosh.pos.system.payload.dto.ProductDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;
import com.zosh.zosh.pos.system.repository.CategoryRepository;
import com.zosh.zosh.pos.system.repository.ProductRepository;
import com.zosh.zosh.pos.system.service.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
	@Override
	public ProductResponseDto createProduct(ProductDto dto) {
		
		Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() ->  new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()
                ));
		
		Product product = new Product();
		product.setName(dto.getName());	
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setStatus(dto.getStatus());
		product.setCategory(category);
		
		Product savedProduct = productRepository.save(product);
		return ProductMapper.toResponse(savedProduct);
		
	
		
	}

	@Override
	public ProductResponseDto getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new ProductNotFoundException(
						"ProductNotFound with id :"+id));
		
		return ProductMapper.toResponse(product);
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		 return productRepository.findAll()
	                .stream()
	                .map(ProductMapper::toResponse)
	                .toList();
		
	}

}
