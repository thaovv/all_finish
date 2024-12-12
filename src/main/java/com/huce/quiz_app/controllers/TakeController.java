package com.huce.quiz_app.controllers;


import com.huce.quiz_app.dto.ResponseObject;
import com.huce.quiz_app.dto.TakeDto;
import com.huce.quiz_app.iservices.ITakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/take")
public class TakeController {
    @Autowired
    private ITakeService takeService;

    @PostMapping("/take-answer")
    public ResponseEntity<ResponseObject> takeAnswer(@RequestBody TakeDto takeDto) {
        TakeDto createdTake = takeService.takeAnswer(takeDto);

        if (createdTake != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(200, "Submitted Successfully", createdTake));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "Cannot Submit", null));
    }

//    @GetMapping("get-take/{takeId}")
//    public ResponseEntity<ResponseObject> getTakeByQuizId(@PathVariable Long takeId) {
//        TakeDto allQuizs = takeService.(userId);
//
//        if (take.isPresent()) {
//            return ResponseEntity.ok(new ResponseObject(200, "", take.get()));
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(404, "Not Found", null));
//        }
//    }
}