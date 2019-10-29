package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class CourseTypeRequestDTO {
    private String name;
    private String description;
    private int minScore;
    private int numberQuestion;
}
