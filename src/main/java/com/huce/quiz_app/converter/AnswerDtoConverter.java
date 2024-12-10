package com.huce.quiz_app.converter;

import com.huce.quiz_app.dto.AnswerDto;
import com.huce.quiz_app.entities.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AnswerDto convertAnswerToQuestionDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    public Answer convertAnswerDtoToAnswer(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }
}