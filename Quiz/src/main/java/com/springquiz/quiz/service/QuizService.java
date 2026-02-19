package com.springquiz.quiz.service;

import com.springquiz.quiz.DAO.DaoLayer;
import com.springquiz.quiz.DAO.QuizDaoLayer;
import com.springquiz.quiz.Model.Quiz;
import com.springquiz.quiz.Model.Responses;
import com.springquiz.quiz.Model.questions;
import com.springquiz.quiz.Model.questionwrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDaoLayer quizDao;

    @Autowired
    DaoLayer quesDao;

    public ResponseEntity<String> createQuizService(String category, int numQ, String title){
        try {
            //pageable is ued to set the limit of the page from metioning start and end pagenumber , its simple equals to LIMIT in sql i.e LIMIT x OFFSET y => behind the scene it will convert pageable to limit and offset
            Pageable pageable= PageRequest.of(0,numQ);

            List<questions> questions = quesDao.findRandomQuestionsByCategory(category,pageable);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestion(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created ", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Quiz is not Created ", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<questionwrapper>> getQuiz(int id) {
      Optional<Quiz> quiz= quizDao.findById(id);
      List<questionwrapper> queswrap= new ArrayList<>();

      try {
          if (quiz.isPresent()) {
              List<questions> questionFromDb = quiz.get().getQuestion();
              for (questions q : questionFromDb) {
                  questionwrapper qw = new questionwrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                  queswrap.add(qw);
              }
              return new ResponseEntity<>(queswrap, HttpStatus.OK);
          }
          return new ResponseEntity<>(queswrap, HttpStatus.BAD_REQUEST);
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
        return new ResponseEntity<>(queswrap, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> getQuizScore(int Qzid, List<Responses> ress) {

            int rightAns = 0, i = 0;
            try{
               Optional<Quiz> quiz = quizDao.findById(Qzid);
               List<questions> quesn = quiz.get().getQuestion();

            for (Responses res : ress) {
                if (res.getResponse().equals(quesn.get(i).getAnswer())) {
                    rightAns++;
                }
                i++;
            }
            i=0;
            return new ResponseEntity<>(rightAns, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(rightAns, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteEntireQuiz() {
        try {

            quizDao.deleteAll();
            return new ResponseEntity<>("completely wiped Out", HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed To  wipe Out" , HttpStatus.BAD_REQUEST);
    }
}
