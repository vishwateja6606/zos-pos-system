package com.zosh.zosh.pos.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.zosh.pos.system.modal.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);

}
