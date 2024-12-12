package com.huce.quiz_app.repositories;

import com.huce.quiz_app.entities.TakeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeAnswerRepository extends JpaRepository<TakeAnswer, Long> {
}