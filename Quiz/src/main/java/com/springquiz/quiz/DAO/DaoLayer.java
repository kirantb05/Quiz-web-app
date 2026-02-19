package com.springquiz.quiz.DAO;

import com.springquiz.quiz.Model.questions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoLayer extends JpaRepository<questions,Integer> {

    @Query(value = "select * from questions q where q.category =:category AND id >= (select Floor(RAND() * (select MAX(id) from questions)))",nativeQuery = true)
    List<questions> findRandomQuestionsByCategory(@Param("category") String category , Pageable pageable);

    List<questions> findByCategory(String category);
}
