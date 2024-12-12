package com.huce.quiz_app.repositories;

import com.huce.quiz_app.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question q WHERE q.quiz_id=:quizId ORDER BY RAND()", nativeQuery = true)
    List<Question> findByQuizId(Long quizId);
}