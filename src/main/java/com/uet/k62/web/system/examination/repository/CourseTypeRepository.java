package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
    Page<CourseType> findAllByDeletedIsFalse(Pageable paging);
    CourseType findByIdAndDeletedIsFalse(Integer id);
}
