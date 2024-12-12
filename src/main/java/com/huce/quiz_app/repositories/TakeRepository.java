package com.huce.quiz_app.repositories;

import com.huce.quiz_app.entities.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeRepository extends JpaRepository<Take, Long> {
}
