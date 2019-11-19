package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {
    List<ExamResult> findByUserId(Integer userId);
    List<ExamResult> findByCourseId(Integer courseId);
    List<ExamResult> findByUserIdAndCourseId(Integer userId, Integer courseId);
}
