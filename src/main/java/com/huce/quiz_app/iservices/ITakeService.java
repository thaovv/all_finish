package com.huce.quiz_app.iservices;

import com.huce.quiz_app.dto.TakeDto;

public interface ITakeService {
    TakeDto takeAnswer(TakeDto takeDto);

}