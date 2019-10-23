package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private int questionTypeId;
    private String content;
    private String imageUrl;
}
