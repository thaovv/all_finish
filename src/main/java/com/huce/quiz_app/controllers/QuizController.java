package com.huce.quiz_app.controllers;

import com.huce.quiz_app.dto.QuizDto;
import com.huce.quiz_app.dto.ResponseObject;
import com.huce.quiz_app.entities.Quiz;
import com.huce.quiz_app.iservices.IQuizService;
import com.huce.quiz_app.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private IQuizService quizService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create-quiz")
    public ResponseEntity<ResponseObject> createQuiz(HttpServletRequest request, @Valid @RequestBody QuizDto quizDto) throws ChangeSetPersister.NotFoundException {
        Long userId = jwtService.getUserId(request);

        if (Objects.equals(userId, quizDto.getUserId())) {
            QuizDto createdQuiz = quizService.createQuiz(quizDto);

            if (createdQuiz != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(201, "Created Successfully", createdQuiz));
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Cannot Create Quiz", null));
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
    public ResponseEntity<ResponseObject> updateQuiz(HttpServletRequest request, @PathVariable Long id, @RequestBody QuizDto quizDto) throws ChangeSetPersister.NotFoundException {
        Long userId = jwtService.getUserId(request);

        QuizDto updatedQuiz = quizService.updateQuiz(id, quizDto, userId);

        if (updatedQuiz != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(200, "Updated Successfully", updatedQuiz));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Cannot Update Quiz", null));
    }

    @DeleteMapping("/delete-quiz/{id}")
    public ResponseEntity<ResponseObject> deleteQuiz(HttpServletRequest request, @PathVariable Long id) {
        if (quizService.deleteQuiz(id, jwtService.getUserId(request)))
            return ResponseEntity.ok(new ResponseObject(200, "Deleted Successfully", null));

        return ResponseEntity.ok(new ResponseObject(200, "Cannot Delete Quiz", null));
    }

    @GetMapping("/get-quizs/{userId}")
    public ResponseEntity<ResponseObject> getQuizsForUser(HttpServletRequest request, @PathVariable Long userId) {
        Long userIdToken = jwtService.getUserId(request);
        if (Objects.equals(userId, userIdToken)){
            List<QuizDto> allQuizs = quizService.getQuizsForUser(userId);
            return ResponseEntity.ok(new ResponseObject(200, "", allQuizs));
        }
        return ResponseEntity.ok(new ResponseObject(200, "Cannot Get Quiz For This User", null));
    }
}