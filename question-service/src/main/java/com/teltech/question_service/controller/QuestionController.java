package com.teltech.question_service.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teltech.question_service.model.Question;
import com.teltech.question_service.model.QuestionWrapper;
import com.teltech.question_service.model.Response;
import com.teltech.question_service.service.QuestionService;


@RestController
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionservice;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            logger.info("Fetching all questions.");
            List<Question> questions = questionservice.getAllQuestions();
            logger.info("Successfully fetched {} questions.", questions.size());
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all questions", e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("category/{categoryName}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("categoryName") String category) {
        try {
            logger.info("Fetching questions for category: {}", category);
            return questionservice.getQuestionsByCategory(category);
        } catch (Exception e) {
            logger.error("Error fetching questions for category: {}", category, e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try {
            logger.info("Adding new question: {}", question);
            return questionservice.addQuestion(question);
        } catch (Exception e) {
            logger.error("Error adding question: {}", question, e);
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        try {
            logger.info("Updating question with ID: {}", question.getId());
            return questionservice.updateQuestion(question);
        } catch (Exception e) {
            logger.error("Error updating question with ID: {}", question.getId(), e);
            return new ResponseEntity<>("Failed to update question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
        try {
            logger.info("Deleting question with ID: {}", question.getId());
            return questionservice.deleteQuestion(question);
        } catch (Exception e) {
            logger.error("Error deleting question with ID: {}", question.getId(), e);
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, Integer numOfQuestions) {
        try {
            logger.info("Generating quiz with {} questions for category: {}", numOfQuestions, categoryName);
            return questionservice.getQuestionsForQuiz(categoryName, numOfQuestions);
        } catch (Exception e) {
            logger.error("Error generating quiz for category: {}", categoryName, e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds) {
        try {
            logger.info("Fetching questions with IDs: {}", questionIds);
            return questionservice.getQuestionsById(questionIds);
        } catch (Exception e) {
            logger.error("Error fetching questions with IDs: {}", questionIds, e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        try {
            logger.info("Calculating score for responses: {}", responses);
            return questionservice.getScore(responses);
        } catch (Exception e) {
            logger.error("Error calculating score for responses: {}", responses, e);
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}