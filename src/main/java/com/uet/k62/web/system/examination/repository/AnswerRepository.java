package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AnswerRepository extends JpaRepository<Answer, BigInteger> {

}