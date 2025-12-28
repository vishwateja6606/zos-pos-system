package com.zosh.zosh.pos.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zosh.zosh.pos.system.modal.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);

}
