package com.zosh.zosh.pos.system.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;
    private List<ProductResponseDto> products;
}

