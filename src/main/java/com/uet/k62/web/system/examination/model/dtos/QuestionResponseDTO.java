package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class QuestionResponseDTO {
    private BigInteger id;
    private int questionTypeId;
    private String content;
    private String imageUrl;
    private Date createdDate;
    private Date updatedDate;
}
