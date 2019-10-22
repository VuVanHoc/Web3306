package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.error.QuestionNotFoundException;
import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CorrectAnswerResponseDTO;
import com.uet.k62.web.system.examination.model.dtos.QuestionAnswerResponseDTO;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;
import com.uet.k62.web.system.examination.model.dtos.QuestionResponseDTO;
import com.uet.k62.web.system.examination.model.entity.Answer;
import com.uet.k62.web.system.examination.model.entity.Question;
import com.uet.k62.web.system.examination.repository.AnswerRepository;
import com.uet.k62.web.system.examination.repository.QuestionRepository;
import com.uet.k62.web.system.examination.service.QuestionService;
import com.uet.k62.web.system.examination.service.QuestionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
//@Transactional(rollbackFor = RollBackException.class)
public class QuestionServiceImpl implements QuestionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuestionTypeService questionTypeService;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionTypeService questionTypeService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionTypeService = questionTypeService;
    }

    @Override
    public RestBody createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionRequestDTO, question);

        questionRepository.save(question);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        //Convert to dto
        BeanUtils.copyProperties(question, questionResponseDTO);
        return RestBody.success(questionResponseDTO);
    }

    @Override
    public RestBody updateQuestion(QuestionRequestDTO dto, BigInteger id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        BeanUtils.copyProperties(dto, question);
        question.setUpdatedDate(new Date());
        questionRepository.save(question);
        return RestBody.success(question);
    }

    @Override
    public RestBody deleteQuestion(BigInteger id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        question.setDeleted(true);

        questionRepository.save(question);
        return RestBody.success(null);
    }

    @Override
    public RestBody getAllQuestions() {
        List<Question> questions = questionRepository.findAllByDeletedIsFalse();

        return RestBody.success(questions);
    }

    @Override
    public RestBody getQuestion(BigInteger id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }

        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item->answers.add(item.getContent()));

        QuestionAnswerResponseDTO responseDTO = new QuestionAnswerResponseDTO();
        BeanUtils.copyProperties(question, responseDTO);
        responseDTO.setAnswers(answers);

        return RestBody.success(responseDTO);
    }

    @Override
    public RestBody getCorrectAnswer(BigInteger id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item->answers.add(item.getContent()));

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for(int i = 0; i < answerEntities.size(); i++){
            if(answerEntities.get(i).isStatus()){ // answer.getStatus = true
                arrayList.add(i);
            }
        }
        int correctIndex[] = new int[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            correctIndex[i] = arrayList.get(i);
        }

        CorrectAnswerResponseDTO responseDTO = new CorrectAnswerResponseDTO();
        responseDTO.setQuestion_id(id);
        responseDTO.setAnswers(answers);
        responseDTO.setCorrectIndex(correctIndex);
        return RestBody.success(responseDTO);
    }
}
