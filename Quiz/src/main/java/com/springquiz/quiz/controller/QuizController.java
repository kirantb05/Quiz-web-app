package com.springquiz.quiz.controller;

import com.springquiz.quiz.Model.Responses;
import com.springquiz.quiz.Model.questions;
import com.springquiz.quiz.Model.questionwrapper;
import com.springquiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService qserv;

    @GetMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
            return qserv.createQuizService(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<questionwrapper>> getQuizID(@PathVariable int id){
        return qserv.getQuiz(id);
    }


    @PostMapping("/submit/{Qzid}")
    public ResponseEntity<Integer> getScore(@PathVariable int Qzid, @RequestBody List<Responses> ress){
       return qserv.getQuizScore(Qzid,ress);
    }

    @GetMapping("/resetQuiz")
    public ResponseEntity<String> deleteEntireQuiz(){


        return qserv.deleteEntireQuiz();
    }

}
