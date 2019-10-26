package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionId(Integer questionId);

    @Override
    void deleteById(Integer integer);
}