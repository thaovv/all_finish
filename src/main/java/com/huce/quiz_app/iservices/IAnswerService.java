package com.huce.quiz_app.iservices;

import com.huce.quiz_app.entities.Answer;
import com.huce.quiz_app.entities.Question;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IAnswerService {
    Answer createAnswer(Answer answer);

    List<Answer> getAllAnswer();

    List<Answer> getAnswersForQuestion(Long quizId);

    Optional<Answer> getAnswerById(Long id);

    Answer updateAnswer(Long id, Answer answer) throws ChangeSetPersister.NotFoundException;

    void deleteAnswer(Long id);
}
