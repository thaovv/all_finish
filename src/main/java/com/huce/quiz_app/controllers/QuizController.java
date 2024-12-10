package com.huce.quiz_app.controllers;

import com.huce.quiz_app.dto.QuizDto;
import com.huce.quiz_app.dto.ResponseObject;
import com.huce.quiz_app.entities.Quiz;
import com.huce.quiz_app.iservices.IQuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private IQuizService quizService;


    @PostMapping("/create-quiz")
    public ResponseEntity<ResponseObject> createQuiz(@Valid @RequestBody QuizDto quizDto) throws ChangeSetPersister.NotFoundException {
        QuizDto createdQuiz = quizService.createQuiz(quizDto);

        if (createdQuiz != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(200, "Created Successfully", createdQuiz));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Cannot Create Quiz", null));
    }

    @GetMapping("/get-all-quiz")
    public ResponseEntity<ResponseObject> getAllQuizs() {
        List<QuizDto> quizs = quizService.getAllQuiz();
        return ResponseEntity.ok(new ResponseObject(200, "", quizs));
    }

    @GetMapping("/get-quiz/{id}")
    public ResponseEntity<ResponseObject> getQuizById(@PathVariable Long id) {
        Optional<QuizDto> quiz = quizService.getQuizById(id);

        if (quiz.isPresent()) {
            return ResponseEntity.ok(new ResponseObject(200, "", quiz.get()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(404, "Not Found", null));
        }
    }

    @PutMapping("/update-quiz/{id}")
    public ResponseEntity<ResponseObject> updateQuiz(@PathVariable Long id, @RequestBody QuizDto quizDto) throws ChangeSetPersister.NotFoundException {
        QuizDto updatedQuiz = quizService.updateQuiz(id, quizDto);

        if (updatedQuiz != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(200, "Updated Successfully", updatedQuiz));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Cannot Update Quiz", null));
    }

    @DeleteMapping("/delete-quiz/{id}")
    public ResponseEntity<ResponseObject> deleteQuiz(@PathVariable Long id) {
        if (quizService.deleteQuiz(id)) return ResponseEntity.ok(new ResponseObject(200, "Deleted Successfully", null));

        return ResponseEntity.ok(new ResponseObject(200, "Cannot Delete Quiz", null));
    }

    @GetMapping("/get-quizs/{userId}")
    public ResponseEntity<ResponseObject> getQuizsForUser(@PathVariable Long userId) {
        List<QuizDto> allQuizs = quizService.getQuizsForUser(userId);
        return ResponseEntity.ok(new ResponseObject(200, "", allQuizs));
    }
}