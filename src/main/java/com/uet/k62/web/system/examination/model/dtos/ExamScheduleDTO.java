package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ExamScheduleDTO {
    private String startTime;
    private String endTime;
    private String note;
}
