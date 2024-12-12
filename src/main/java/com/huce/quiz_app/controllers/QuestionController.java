package com.huce.quiz_app.controllers;

import com.huce.quiz_app.dto.QuestionDto;
import com.huce.quiz_app.dto.ResponseObject;
import com.huce.quiz_app.iservices.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("/get-questions/{quizId}")
    public ResponseEntity<ResponseObject> getQuestionsForQuiz(@PathVariable Long quizId) {
        List<QuestionDto> quizs = questionService.getQuestionsForQuiz(quizId);

        return ResponseEntity.ok(new ResponseObject(200, "Questions For Quiz", quizs));
    }
}
