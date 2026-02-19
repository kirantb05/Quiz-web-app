package com.springquiz.quiz.service;


import com.springquiz.quiz.DAO.DaoLayer;
import com.springquiz.quiz.Model.questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    DaoLayer repo;

    public ResponseEntity<List<questions>> getAllQuestions(){
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<questions>> getCategory(String category){
        try {
            return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(questions ques){
        try {
            repo.save(ques);
            return new ResponseEntity<>("Done With Adding Question", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Not Added the Question", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> deleteQuestion(int id){
        try{
            repo.deleteById(id);
            return new ResponseEntity<>(" Deleted The Question ",HttpStatus.ACCEPTED);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(" Question i Not Deleted ",HttpStatus.BAD_REQUEST);
    }

}
