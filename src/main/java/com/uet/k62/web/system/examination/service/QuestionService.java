package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;

import java.math.BigInteger;

public interface QuestionService {
    RestBody createQuestion(QuestionRequestDTO dto);
    RestBody updateQuestion(QuestionRequestDTO dto, BigInteger id);
    RestBody deleteQuestion(BigInteger id);
    RestBody getAllQuestions();
    RestBody getQuestion(BigInteger id);
    RestBody getCorrectAnswer(BigInteger id);
}
