package com.saurav.quiz_service.repository;

import com.saurav.quiz_service.entity.Quiz;
import com.saurav.quiz_service.projection.QuizAnsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QuizRepository extends JpaRepository<Quiz, Integer> {

//    @Query("SELECT q.id AS id, q.ans AS answer " +
//            "FROM Question q WHERE q.quiz.id = :id")
//    List<QuizAnsKey> findCorrectAnswers(@Param("id") Integer id);
}
