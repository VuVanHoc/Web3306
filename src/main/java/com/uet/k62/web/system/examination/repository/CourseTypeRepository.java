package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
    List<CourseType> findAllByDeletedIsFalse();
    CourseType findByIdAndDeletedIsFalse(Integer id);
}
