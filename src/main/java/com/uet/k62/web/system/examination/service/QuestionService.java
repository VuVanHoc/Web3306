package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;

import java.math.BigInteger;

public interface QuestionService {
    RestBody createQuestion(QuestionRequestDTO dto);
    RestBody updateQuestion(QuestionRequestDTO dto, Integer id);
    RestBody deleteQuestion(Integer id);
    RestBody getAllQuestions(Integer pageNo, Integer pageSize);
    RestBody getQuestion(Integer id);
    RestBody getCorrectAnswer(Integer id);
}
