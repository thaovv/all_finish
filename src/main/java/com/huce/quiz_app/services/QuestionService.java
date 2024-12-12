package com.huce.quiz_app.services;

import com.huce.quiz_app.converter.QuestionDtoConverter;
import com.huce.quiz_app.dto.QuestionDto;
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

    @Autowired
    private QuestionDtoConverter questionDtoConverter;

    @Override
    public Question createQuestion(Question question) {
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        return List.of();
    }

    @Override
    public List<QuestionDto> getQuestionsForQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId)
                .stream()
                .map(question -> questionDtoConverter.convertQuestionToQuestionDto(question))
                .toList();
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

