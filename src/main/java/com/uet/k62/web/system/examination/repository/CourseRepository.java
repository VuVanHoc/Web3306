package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByIdAndDeletedIsFalse(Integer courseId);
    Page<Course> findAllByDeletedIsFalse(Pageable paging);

}
