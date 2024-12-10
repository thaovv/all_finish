package com.huce.quiz_app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "take")
public class Take {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float score;

    private LocalDateTime createAt;

    @OneToMany(mappedBy="take")
    private List<TakeAnswer> takeAnswers;

    @ManyToOne
    @JoinColumn(name = "userId", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quizId", nullable=false)
    private Quiz quiz;

    public Take() {
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
}
