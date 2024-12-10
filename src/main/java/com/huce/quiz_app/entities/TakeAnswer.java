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

    public TakeAnswer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
