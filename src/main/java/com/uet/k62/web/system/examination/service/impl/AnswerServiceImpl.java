package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.AnswerRequestDTO;
import com.uet.k62.web.system.examination.model.dtos.AnswerResponseDTO;
import com.uet.k62.web.system.examination.model.entity.Answer;
import com.uet.k62.web.system.examination.repository.AnswerRepository;
import com.uet.k62.web.system.examination.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AnswerServiceImpl.class);

    private AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public RestBody createAnswer(AnswerRequestDTO answerRequestDTO) {
        List<Answer> answers = new ArrayList<>();

        List<String> answers_input = answerRequestDTO.getAnswers();
        answers_input.forEach(item -> answers.add(new Answer(answerRequestDTO.getQuestionId(), item, false)));
        int[] correctIndex = answerRequestDTO.getCorrectIndex();
        Arrays.stream(correctIndex).forEach(index -> answers.get(index).setStatus(true));

        answerRepository.saveAll(answers);

        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
        BeanUtils.copyProperties(answerRequestDTO, answerResponseDTO);
        return RestBody.success(answerResponseDTO);
    }

    @Override
    public RestBody updateAnswer(AnswerRequestDTO answerRequestDTO) {
        return null;
    }

    @Override
    public RestBody deleteAnswer(Integer questionId) {
        List<Answer> answers = answerRepository.findAllByQuestionId(questionId);
        answers.forEach(item -> answerRepository.deleteById(item.getId()));
        return RestBody.success("Deleted");
    }
    
    
    public List<Answer> getListAnswerOfQuestion(int questionId){
    	return answerRepository.findAllByQuestionId(questionId);
    }
}
