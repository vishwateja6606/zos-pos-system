package com.zosh.zosh.pos.system.payload.dto;



import com.zosh.zosh.pos.system.domain.Status;

import lombok.Data;

@Data
public class ProductDto {
private Long id;
	
	private String name;
	private String  description;
	private Double  price;
	private  Integer stock;
	private Status status;
	private Long categoryId;
}
