package com.uet.k62.web.system.examination.restcontroller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.CreateQuestionAndAnswerRequest;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;
import com.uet.k62.web.system.examination.paging.PageConstant;
import com.uet.k62.web.system.examination.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api/questions")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @ApiOperation(value = "Get all questions", response = RestBody.class)
    @GetMapping
    public ResponseEntity getAllQuestions(@RequestParam(defaultValue = PageConstant.PAGE_NO) Integer pageNo,
                                          @RequestParam(defaultValue = PageConstant.PAGE_SIZE) Integer pageSize){
        RestBody restBody = questionService.getAllQuestions(pageNo, pageSize);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Create new question", response = RestBody.class)
    @PostMapping
    public ResponseEntity createNewQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        RestBody restBody = questionService.createQuestion(questionRequestDTO);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get a question", response = RestBody.class)
    @GetMapping(value = "{id}")
    public ResponseEntity getQuestion(@PathVariable Integer id){
        RestBody body = questionService.getQuestion(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update a question", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity updateQuestion(@PathVariable Integer id, @RequestBody QuestionRequestDTO questionRequestDTO){
        RestBody body = questionService.updateQuestion(questionRequestDTO, id);
        return  ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a question", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteQuestion(@PathVariable Integer id){
        RestBody body = questionService.deleteQuestion(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get correct answer of a question", response = RestBody.class)
    @GetMapping(value = "{id}/answers")
    public ResponseEntity getCorrectAnswers(@PathVariable Integer id){
        RestBody body = questionService.getCorrectAnswer(id);
        return ResponseEntity.ok(body);
    }
    
    
    @PostMapping(value = "create-new-question")
	public ResponseEntity createQuestion(@RequestBody CreateQuestionAndAnswerRequest createQuestionAndAnswerRequest){
	    RestBody restBody = questionService.createQuestionAndAnswer(createQuestionAndAnswerRequest);
	    
	    return ResponseEntity.ok(restBody);
    }
}


