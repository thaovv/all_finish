package com.huce.quiz_app.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TakeDto {
    private Long id;

    private float score = 0;

    private LocalDateTime createAt = LocalDateTime.now();

    private Long quizId;

    private Long userId;

    private List<TakeAnswerDto> takeAnswers;

    public List<TakeAnswerDto> getTakeAnswers() {
        return takeAnswers;
    }

    public void setTakeAnswers(List<TakeAnswerDto> takeAnswers) {
        this.takeAnswers = takeAnswers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
