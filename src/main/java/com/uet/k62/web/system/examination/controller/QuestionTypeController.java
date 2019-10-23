package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.QuestionTypeDTO;
import com.uet.k62.web.system.examination.service.QuestionTypeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value="api/question-types")
public class QuestionTypeController {
    private QuestionTypeService questionTypeService;

    public QuestionTypeController(QuestionTypeService questionTypeService){
        this.questionTypeService = questionTypeService;
    }

    @ApiOperation(value = "Get all question types", response = RestBody.class)
    @GetMapping
    public ResponseEntity getAllQuestionTypes(){
        RestBody restBody = questionTypeService.getAllQuestionTypes();
        return ResponseEntity.ok(restBody);
    }


    @ApiOperation(value = "Create new question type", response = RestBody.class)
    @PostMapping
    public ResponseEntity createQuestionType(@RequestBody QuestionTypeDTO dto){
        RestBody restBody = questionTypeService.createQuestionType(dto);
        return ResponseEntity.ok(restBody);
    }

    @ApiOperation(value = "Get a single question type data", response = RestBody.class)
    @GetMapping(value = "{id}")
    public ResponseEntity getQuestionType(@PathVariable BigInteger id){
        RestBody body = questionTypeService.getQuestionType(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update a question type record", response = RestBody.class)
    @PutMapping(value = "{id}")
    public ResponseEntity updateQuestionType(@RequestBody QuestionTypeDTO questionTypeDTO, @PathVariable BigInteger id){
        RestBody body = questionTypeService.updateQuestionType(questionTypeDTO, id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a question type record", response = RestBody.class)
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteQuestionType(@PathVariable BigInteger id){
        RestBody body = questionTypeService.deleteQuestionType(id);
        return ResponseEntity.ok(body);
    }
}
