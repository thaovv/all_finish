package com.huce.quiz_app.iservices;

import com.huce.quiz_app.dto.QuestionDto;
import com.huce.quiz_app.entities.Question;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    Question createQuestion(Question question);

    List<Question> getAllQuestion();

    List<QuestionDto> getQuestionsForQuiz(Long quizId);

    Optional<Question> getQuestionById(Long id);

    Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException;

    void deleteQuestion(Long id);
}