package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, BigInteger> {
    List<QuestionType> findAllByDeletedIsFalse();
    QuestionType findOneByCode(String code);
    QuestionType findOneByIdAndDeletedIsFalse(BigInteger id);
}
