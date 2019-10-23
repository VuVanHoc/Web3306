package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<User, BigInteger> {
	
	List<User> findAllBy();
	
	User findByUsername(String username);
	
	User findById(int id);
	
}
