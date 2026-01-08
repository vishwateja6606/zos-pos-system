package com.zosh.zosh.pos.system.payload.response;


import com.zosh.zosh.pos.system.domain.Status;

import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Status status;
}

