package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionTypeDTO;

import java.math.BigInteger;

public interface QuestionTypeService {
    RestBody createQuestionType(QuestionTypeDTO dto);
    RestBody updateQuestionType(QuestionTypeDTO dto, Integer id);
    RestBody deleteQuestionType(Integer id);
    RestBody getQuestionType(Integer id);
    RestBody getAllQuestionTypes();
}
