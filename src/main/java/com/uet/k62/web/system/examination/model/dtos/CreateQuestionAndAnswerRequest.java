package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionAndAnswerRequest {
	
	private String questionType;
	private String question;
	private String image;
	private List<CreateAnswerDTO> answerDTOList;
	
	
}
