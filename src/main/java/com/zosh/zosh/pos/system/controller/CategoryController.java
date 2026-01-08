package com.zosh.zosh.pos.system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.zosh.pos.system.payload.dto.CategoryDto;
import com.zosh.zosh.pos.system.payload.response.CategoryResponseDto;
import com.zosh.zosh.pos.system.payload.response.ProductResponseDto;
import com.zosh.zosh.pos.system.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // CREATE CATEGORY
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(
            @RequestBody CategoryDto dto) {

        CategoryResponseDto response = categoryService.createCategory(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET CATEGORY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // GET PRODUCTS BY CATEGORY
    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(
            @PathVariable Long id) {

        return ResponseEntity.ok(categoryService.getProductsByCategory(id));
    }
}
