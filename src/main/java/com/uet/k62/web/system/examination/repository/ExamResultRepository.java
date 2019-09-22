package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ExamResultRepository extends JpaRepository<ExamResult, BigInteger> {
}
