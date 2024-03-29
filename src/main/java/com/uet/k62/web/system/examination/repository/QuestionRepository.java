package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByDeletedIsFalse();
    Question findOneByIdAndDeletedIsFalse(Integer id);
}
