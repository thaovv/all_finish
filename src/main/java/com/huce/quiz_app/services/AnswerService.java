package com.huce.quiz_app.services;

import com.huce.quiz_app.entities.Answer;
import com.huce.quiz_app.iservices.IAnswerService;
import com.huce.quiz_app.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer createAnswer(Answer answer) {
        return null;
    }

    @Override
    public List<Answer> getAllAnswer() {
        return List.of();
    }

    @Override
    public List<Answer> getAnswersForQuestion(Long quizId) {
        return List.of();
    }

    @Override
    public Optional<Answer> getAnswerById(Long id) {
        return Optional.empty();
    }

    @Override
    public Answer updateAnswer(Long id, Answer answer) throws ChangeSetPersister.NotFoundException {
        return null;
    }

    @Override
    public void deleteAnswer(Long id) {

    }
}
