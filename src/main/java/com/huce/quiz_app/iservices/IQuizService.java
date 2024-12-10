package com.huce.quiz_app.iservices;

import com.huce.quiz_app.dto.QuizDto;
import com.huce.quiz_app.entities.Quiz;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IQuizService {
    QuizDto createQuiz(QuizDto quizDto);

    List<QuizDto> getAllQuiz();

    List<QuizDto> getQuizsForUser(Long userId);

    Optional<QuizDto> getQuizById(Long id);

    QuizDto updateQuiz(Long id, QuizDto quizDto);

    Boolean deleteQuiz(Long id);
}
