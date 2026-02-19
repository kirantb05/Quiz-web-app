package com.springquiz.quiz.DAO;

import com.springquiz.quiz.Model.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface QuizDaoLayer extends JpaRepository<Quiz,Integer> {
}
