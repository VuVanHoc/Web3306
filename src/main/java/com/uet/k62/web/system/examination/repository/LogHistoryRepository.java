package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LogHistoryRepository extends JpaRepository<LogHistory, BigInteger> {
}
