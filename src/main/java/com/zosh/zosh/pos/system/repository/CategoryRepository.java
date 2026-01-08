package com.zosh.zosh.pos.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.zosh.pos.system.modal.Category;

public interface CategoryRepository  extends JpaRepository<Category,Long>{

}
