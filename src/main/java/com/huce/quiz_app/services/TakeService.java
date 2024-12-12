package com.huce.quiz_app.services;

import com.huce.quiz_app.converter.TakeDtoConverter;
import com.huce.quiz_app.dto.TakeDto;
import com.huce.quiz_app.entities.Take;
import com.huce.quiz_app.entities.*;
import com.huce.quiz_app.iservices.ITakeService;
import com.huce.quiz_app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TakeService implements ITakeService {
    @Autowired
    private TakeRepository takeRepository;

    @Autowired
    private TakeAnswerRepository takeAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TakeDtoConverter takeDtoConverter;

    @Override
    public TakeDto takeAnswer(TakeDto takeDto) {
        Optional<User> user = userRepository.findById(takeDto.getUserId());

        if (user.isPresent()) {
            Take take = takeDtoConverter.convertTakeDtoToTake(takeDto);

            float totalScoreTake = take.getTakeAnswers().stream()
                    .filter(takeAnswer -> {
                        return answerRepository.findById(takeAnswer.getAnswer().getId())
                                .map(Answer::getCorrect)
                                .orElse(false);
                    })
                    .map(takeAnswer -> {
                        return questionRepository.findById(takeAnswer.getQuestion().getId())
                                .map(Question::getScore)
                                .orElse(0F);
                    })
                    .reduce(0F, Float::sum);

            take.setScore(totalScoreTake);
            return takeDtoConverter.convertTakeToTakeDto(takeRepository.save(take));
        } else {
            return null;
        }
    }
}