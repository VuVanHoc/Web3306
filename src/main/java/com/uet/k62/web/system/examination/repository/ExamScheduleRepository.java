package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, BigInteger> {
}
