package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.ExamScheduleDTO;

public interface ExamScheduleService {
    RestBody createExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId);
    RestBody updateExamSchedule(ExamScheduleDTO examScheduleDTO, Integer courseId);
    RestBody getExamSchedule(Integer courseId);
    RestBody deleteExamSchedule(Integer courseId);

}
