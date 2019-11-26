package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.ExamResultDTO;

public interface ExamResultService {
    RestBody saveExamResult(ExamResultDTO examResultDTO);
    RestBody getExamResult(Integer userId, Integer courseId);
    RestBody getAllResults();
    RestBody getFullResults();
    RestBody getTotalRecord();
    RestBody getPass();
    RestBody countPoint();
}
