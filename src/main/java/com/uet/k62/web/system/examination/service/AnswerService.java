package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.AnswerRequestDTO;

import java.math.BigInteger;

public interface AnswerService {
    RestBody createAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody updateAnswer(AnswerRequestDTO answerRequestDTO);
    RestBody deleteAnswer(Integer questionId);
}
