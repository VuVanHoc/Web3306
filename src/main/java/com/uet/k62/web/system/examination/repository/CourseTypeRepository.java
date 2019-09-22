package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CourseTypeRepository extends JpaRepository<CourseType, BigInteger> {
}
