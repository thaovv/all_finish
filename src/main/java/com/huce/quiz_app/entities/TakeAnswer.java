package com.huce.quiz_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "take_answer")
public class TakeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "takeId", nullable=false)
    private Take take;

    @ManyToOne
    @JoinColumn(name = "answerId", nullable=false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable=false)
    private Question question;

    public Take getTake() {
        return take;
    }

    public void setTake(Take take) {
        this.take = take;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

