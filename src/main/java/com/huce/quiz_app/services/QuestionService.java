package com.huce.quiz_app.services;

import com.huce.quiz_app.entities.Question;
import com.huce.quiz_app.iservices.IQuestionService;
import com.huce.quiz_app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        return List.of();
    }

    @Override
    public List<Question> getQuestionsForQuiz(Long quizId) {
        return List.of();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return Optional.empty();
    }

    @Override
    public Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException {
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {

    }
}
