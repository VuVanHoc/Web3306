package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseUserRepository extends JpaRepository<CourseUser, Integer> {
	
	List<CourseUser> findAllByCourseId(int courseId);
}
