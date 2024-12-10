package com.huce.quiz_app.services;

import com.huce.quiz_app.converter.QuizDtoConverter;
import com.huce.quiz_app.dto.QuizDto;
import com.huce.quiz_app.entities.Answer;
import com.huce.quiz_app.entities.Question;
import com.huce.quiz_app.entities.Quiz;
import com.huce.quiz_app.entities.User;
import com.huce.quiz_app.iservices.IQuizService;
import com.huce.quiz_app.repositories.AnswerRepository;
import com.huce.quiz_app.repositories.QuizRepository;
import com.huce.quiz_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService implements IQuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuizDtoConverter quizDtoConverter;

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Optional<User> user = userRepository.findById(quizDto.getUserId());

        if (user.isPresent()) {
            Quiz quiz = quizDtoConverter.convertQuizDtoToQuiz(quizDto);

            return quizDtoConverter.convertQuizToQuizDto(quizRepository.save(quiz));
        } else {
            return null;
        }
    }

    @Override
    public List<QuizDto> getAllQuiz() {
        return quizRepository.findAll()
                .stream()
                .map(quiz -> quizDtoConverter.convertQuizToQuizDto(quiz))
                .toList();
    }

    @Override
    public List<QuizDto> getQuizsForUser(Long userId) {
        return quizRepository.findByUserId(userId)
                .stream()
                .map(quiz -> quizDtoConverter.convertQuizToQuizDto(quiz))
                .toList();
    }

    @Override
    public Optional<QuizDto> getQuizById(Long id) {
        return quizRepository.findById(id)
                .map(quizDtoConverter::convertQuizToQuizDto);
    }

    @Override
    public QuizDto updateQuiz(Long id, QuizDto quizDto) {
        if (quizDto.getId() != null && !quizDto.getId().equals(id)) {
            return null;
        }

        Optional<Quiz> curentQuiz = quizRepository.findById(id);
        Quiz quiz = quizDtoConverter.convertQuizDtoToQuiz(quizDto);

        if (curentQuiz.isPresent()) {
            Quiz updatedQuiz = curentQuiz.get();
            updatedQuiz.setTitle(quiz.getTitle());
            updatedQuiz.setScore(quiz.getScore());
            updatedQuiz.setPublished(quiz.getPublished());

            List<Question> existingQuestions = (updatedQuiz.getQuestions() == null) ? new ArrayList<>() : updatedQuiz.getQuestions();

            Set<Long> questionIds = quiz.getQuestions()
                    .stream()
                    .map(Question::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            existingQuestions.removeIf(existingQuestion ->
                    existingQuestion.getId() != null && !questionIds.contains(existingQuestion.getId()));


            for (Question question: quiz.getQuestions()) {
                Question updatedQuestion = existingQuestions
                        .stream()
                        .filter(q -> question.getId() != null && q.getId().equals(question.getId()))
                        .findFirst()
                        .orElseGet(() -> new Question());

                updatedQuestion.setContent(question.getContent());
                updatedQuestion.setScore(question.getScore());
                updatedQuestion.setQuiz(updatedQuiz);

                List<Answer> existingAnswers = (updatedQuestion.getAnswers() == null) ? new ArrayList<>() : updatedQuestion.getAnswers();

                Set<Long> answerIds = question.getAnswers()
                        .stream()
                        .map(Answer::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                existingAnswers.removeIf(existingAnswer ->
                        existingAnswer.getId() != null && !answerIds.contains(existingAnswer.getId()));

                for (Answer answer: question.getAnswers()) {
                    Answer updatedAnswer = existingAnswers
                            .stream()
                            .filter(q -> answer.getId() != null && q.getId().equals(answer.getId()))
                            .findFirst()
                            .orElseGet(() -> new Answer());

                    updatedAnswer.setContent(answer.getContent());
                    updatedAnswer.setCorrect(answer.getCorrect());
                    updatedAnswer.setQuestion(updatedQuestion);

                    if (updatedAnswer.getId() == null) {
                        existingAnswers.add(updatedAnswer);
                    }
                }

                updatedQuestion.setAnswers(existingAnswers);

                if (updatedQuestion.getId() == null) {
                    existingQuestions.add(updatedQuestion);
                }
            }

            updatedQuiz.setQuestions(existingQuestions);

            return quizDtoConverter.convertQuizToQuizDto(quizRepository.save(updatedQuiz));
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }

        return false;
    }
}