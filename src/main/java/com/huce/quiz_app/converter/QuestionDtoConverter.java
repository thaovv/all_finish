package com.huce.quiz_app.converter;

import com.huce.quiz_app.dto.QuestionDto;
import com.huce.quiz_app.entities.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public QuestionDto convertQuestionToQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public Question convertQuestionDtoToQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }
}
