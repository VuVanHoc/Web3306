package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class CorrectAnswerResponseDTO {
    private Integer questionId;
    private List<String> answers;
    private int[] correctIndex;
}
