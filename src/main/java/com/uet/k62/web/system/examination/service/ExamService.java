package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionIdsListDTO;

public interface ExamService {
    RestBody getExam(Integer examId);
    RestBody createExam(Integer examId, QuestionIdsListDTO questionIdsListDTO);
    RestBody updateExam(Integer examId, QuestionIdsListDTO questionIdsListDTO);
    RestBody deleteExam(Integer examId);
}
