package com.uet.k62.web.system.examination.repository;

import com.uet.k62.web.system.examination.model.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Page<Question> findAllByDeletedIsFalseOrderByCreatedDateDesc(Pageable paging);
    Question findOneByIdAndDeletedIsFalse(Integer id);
}
