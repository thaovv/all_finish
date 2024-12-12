package com.huce.quiz_app.converter;

import com.huce.quiz_app.dto.TakeDto;
import com.huce.quiz_app.entities.Take;
import com.huce.quiz_app.entities.TakeAnswer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TakeDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TakeDto convertTakeToTakeDto(Take take) {
        return modelMapper.map(take, TakeDto.class);
    }

    public Take convertTakeDtoToTake(TakeDto takeDto) {
        Take take = modelMapper.map(takeDto, Take.class);

        List<TakeAnswer> takeAnswers = new ArrayList<TakeAnswer>();

        for (TakeAnswer takeAnswer : take.getTakeAnswers()) {
            takeAnswer.setTake(take);
            takeAnswers.add(takeAnswer);
        }

        take.setTakeAnswers(takeAnswers);
        return take;
    }
}