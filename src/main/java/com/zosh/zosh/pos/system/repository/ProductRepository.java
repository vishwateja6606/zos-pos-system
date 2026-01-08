package com.zosh.zosh.pos.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.zosh.pos.system.modal.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
