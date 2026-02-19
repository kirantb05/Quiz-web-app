package com.springquiz.quiz.controller;

import com.springquiz.quiz.Model.questions;
import com.springquiz.quiz.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuizQuestions {

    @Autowired
    Service serv;

    //----------------------------------------------------------------

    @GetMapping("/allquestions")
    public ResponseEntity<List<questions>> allQuestions(){
        return serv.getAllQuestions();
    }

    //---------------------------------------------------------------

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<questions>> getByCategory(@PathVariable String cat){
        return serv.getCategory(cat);

    }

    //---------------------------------------------------------------

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestions(@RequestBody questions ques){
         return serv.addQuestion(ques);

    }

    //---------------------------------------------------------------

    @GetMapping("/questionId/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable int id){
        return serv.deleteQuestion(id);

    }

    //---------------------------------------------------------------


}
