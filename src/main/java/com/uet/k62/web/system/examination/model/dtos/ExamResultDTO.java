package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class ExamResultDTO {
    private Integer userId;
    private Integer courseId;
//    private Integer endTime;
    private Integer score;
    private boolean status;
}
