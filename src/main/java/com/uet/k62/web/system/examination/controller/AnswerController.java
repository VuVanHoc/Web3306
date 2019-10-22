package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.AnswerRequestDTO;
import com.uet.k62.web.system.examination.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "api/answers")
public class AnswerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AnswerController.class);

    private AnswerService answerService;

    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @ApiOperation(value = "Create answers for a question", response = RestBody.class)
    @PostMapping
    public ResponseEntity createAnswers(@RequestBody AnswerRequestDTO answerRequestDTO){
        RestBody body = answerService.createAnswer(answerRequestDTO);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete answers of a question", response = RestBody.class)
    @DeleteMapping
    public ResponseEntity deleteAnswers(@RequestParam(value = "question") BigInteger questionId){
        RestBody body = answerService.deleteAnswer(questionId);
        return ResponseEntity.ok(body);
    }
}
