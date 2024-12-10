package com.huce.quiz_app.converter;

import com.huce.quiz_app.dto.QuizDto;
import com.huce.quiz_app.entities.Answer;
import com.huce.quiz_app.entities.Question;
import com.huce.quiz_app.entities.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public QuizDto convertQuizToQuizDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    public Quiz convertQuizDtoToQuiz(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);

        float totalScoreQuestion = 0;
        List<Question> questions = new ArrayList<Question>();

        for (Question question : quiz.getQuestions()) {
            question.setQuiz(quiz);

            List<Answer> answers = new ArrayList<Answer>();

            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
                answers.add(answer);
            }

            question.setAnswers(answers);
            questions.add(question);

            totalScoreQuestion += question.getScore();
        }

        quiz.setQuestions(questions);
        quiz.setScore(totalScoreQuestion);
        return quiz;
    }

    public List<Quiz> convertQuizDtoToQuiz(List<QuizDto> quizDtos) {
        List<Quiz> quizs = quizDtos
                .stream()
                .map(quizDto -> modelMapper.map(quizDto, Quiz.class))
                .toList();
        return quizs;
    }
}