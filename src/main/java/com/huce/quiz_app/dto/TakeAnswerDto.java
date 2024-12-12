package com.huce.quiz_app.dto;

public class TakeAnswerDto {
    private Long id;

    private Long takeId;

    private Long answerId;

    private Long quesitonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuesitonId() {
        return quesitonId;
    }

    public void setQuesitonId(Long quesitonId) {
        this.quesitonId = quesitonId;
    }
}