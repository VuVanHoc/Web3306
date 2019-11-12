package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	Page<User> findAllByDeletedIsFalse(Pageable paging);
	
	User findByUsername(String username);
	
	User findUserById(Integer id);
	
	User findByIdAndDeletedIsFalse(Integer id);
	
	List<User> findAllByRoleId(int roleId);
}
