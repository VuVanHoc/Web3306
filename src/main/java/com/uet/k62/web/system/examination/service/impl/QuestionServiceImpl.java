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
import com.uet.k62.web.system.examination.repository.QuestionTypeRepository;
import com.uet.k62.web.system.examination.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuestionTypeRepository questionTypeRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionTypeRepository questionTypeRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionTypeRepository = questionTypeRepository;
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
    public RestBody updateQuestion(QuestionRequestDTO dto, Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        BeanUtils.copyProperties(dto, question);
        question.setUpdatedDate(new Date());
        questionRepository.save(question);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        BeanUtils.copyProperties(question, questionResponseDTO);
        questionResponseDTO.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
        return RestBody.success(questionResponseDTO);
    }

    @Override
    public RestBody deleteQuestion(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }
        question.setDeleted(true);

        questionRepository.save(question);
        return RestBody.success(null);
    }

    @Override
    public RestBody getAllQuestions(Integer pageNo, Integer pageSize) {
        List<QuestionResponseDTO> questionResponseDTOS = getAllQuestion(pageNo, pageSize);
        return RestBody.success(questionResponseDTOS);
    }

    public List<QuestionResponseDTO> getAllQuestion(int pageNum, int pageSize) {
	    Pageable paging = PageRequest.of(pageNum, pageSize);
	    List<QuestionResponseDTO> questionResponseListDTO = new ArrayList<>();
	    Page<Question> questions = questionRepository.findAllByDeletedIsFalse(paging);
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
	    if (questions.hasContent()) {
		    int index = pageNum * pageSize + 1;
		    for (Question question : questions.getContent()) {
			    QuestionResponseDTO single = new QuestionResponseDTO();
			    BeanUtils.copyProperties(question, single);
			
			    single.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
			    if (single.getQuestionTypeCode().equals("MC")) {
				    single.setQuestionTypeCode("Multiple Choice");
			    } else if (single.getQuestionTypeCode().equals("SA")) {
				    single.setQuestionTypeCode("Fill in blank");
			    } else if (single.getQuestionTypeCode().equals("TF")) {
				    single.setQuestionTypeCode("Yes/No or True/False");
			    }else if(single.getQuestionTypeCode().equals("SO")) {
				    single.setQuestionTypeCode("Select One");
			    }
			    single.setIndex(index);
			    index++;
			    single.setCreatedDate(simpleDateFormat.format(question.getCreatedDate()));
			    questionResponseListDTO.add(single);
		    }
	    }
	    return questionResponseListDTO;
    }
    @Override
    public RestBody getQuestion(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question is not found: " + id);
        }

        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item -> answers.add(item.getContent()));

        QuestionAnswerResponseDTO responseDTO = new QuestionAnswerResponseDTO();
        responseDTO.setQuestionTypeCode(this.getQuestionTypeCode(question.getQuestionTypeId()));
        responseDTO.setAnswers(answers);
        BeanUtils.copyProperties(question, responseDTO);

        return RestBody.success(responseDTO);
    }

    @Override
    public RestBody getCorrectAnswer(Integer id) {
        Question question = questionRepository.findOneByIdAndDeletedIsFalse(id);
        List<Answer> answerEntities = answerRepository.findAllByQuestionId(question.getId());
        List<String> answers = new ArrayList<>();
        answerEntities.forEach(item -> answers.add(item.getContent()));

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < answerEntities.size(); i++) {
            if (answerEntities.get(i).isStatus()) { // answer.getStatus = true
                arrayList.add(i);
            }
        }
	    int[] correctIndex = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            correctIndex[i] = arrayList.get(i);
        }

        CorrectAnswerResponseDTO responseDTO = new CorrectAnswerResponseDTO();
        responseDTO.setQuestionId(id);
        responseDTO.setAnswers(answers);
        responseDTO.setCorrectIndex(correctIndex);
        return RestBody.success(responseDTO);
    }

    private String getQuestionTypeCode(Integer questionTypeId) {
        return questionTypeRepository.findOneByIdAndDeletedIsFalse(questionTypeId).getCode();
    }
    
    public Question getAQuestionById(int id){
    	return questionRepository.findOneByIdAndDeletedIsFalse(id);
    }
}
