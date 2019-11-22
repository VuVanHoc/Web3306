package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class ExamResultFullDTO {
    private Integer index;
    private String fullName;
    private String userName;
    private String coursename;
    private Integer score;
    private String status;
}
