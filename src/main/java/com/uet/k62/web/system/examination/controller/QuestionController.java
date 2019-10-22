package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionRequestDTO;
import com.uet.k62.web.system.examination.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "api/questions")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @ApiOperation(value = "Get all questions", response = RestBody.class)
    @GetMapping
    public ResponseEntity getAllQuestions(){
        RestBody restBody = questionService.getAllQuestions();
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
    public ResponseEntity getQuestion(@PathVariable BigInteger id){
        RestBody body = questionService.getQuestion(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update a question", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity updateQuestion(@PathVariable BigInteger id, @RequestBody QuestionRequestDTO questionRequestDTO){
        RestBody body = questionService.updateQuestion(questionRequestDTO, id);
        return  ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a question", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteQuestion(@PathVariable BigInteger id){
        RestBody body = questionService.deleteQuestion(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get correct answer of a question", response = RestBody.class)
    @GetMapping(value = "{id}/answers")
    public ResponseEntity getCorrectAnswers(@PathVariable BigInteger id){
        RestBody body = questionService.getCorrectAnswer(id);
        return ResponseEntity.ok(body);
    }
}


