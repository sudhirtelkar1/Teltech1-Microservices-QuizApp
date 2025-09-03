package com.teltech.quiz_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teltech.quiz_service.model.Question;
import com.teltech.quiz_service.model.QuestionWrapper;
import com.teltech.quiz_service.model.QuizDto;
import com.teltech.quiz_service.model.Response;
import com.teltech.quiz_service.service.QuizService;

@RestController
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    QuizService quizservice;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            logger.info("Request received to fetch all questions.");
            ResponseEntity<List<Question>> response = quizservice.getAllQuestions();
            logger.info("Successfully fetched all questions.");
            return response;
        } catch (Exception e) {
            logger.error("Error fetching all questions", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        try {
            logger.info("Request received to create quiz with title: {}, category: {}, numOfQuestions: {}",
                        quizDto.getTitle(), quizDto.getCategoryName(), quizDto.getNumOfQuestions());
            ResponseEntity<String> response = quizservice.createQuiz(quizDto.getCategoryName(),
                    quizDto.getNumOfQuestions(), quizDto.getTitle());
            logger.info("Quiz created successfully.");
            return response;
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return ResponseEntity.status(500).body("Error creating quiz");
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer id) {
        try {
            logger.info("Request received to fetch questions for quiz ID: {}", id);
            ResponseEntity<List<QuestionWrapper>> response = quizservice.getQuestionsFromDB(id);
            logger.info("Successfully fetched questions for quiz ID: {}", id);
            return response;
        } catch (Exception e) {
            logger.error("Error fetching questions for quiz ID: {}", id, e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id, @RequestBody List<Response> responses) {
        try {
            logger.info("Request received to calculate result for quiz ID: {}", id);
            ResponseEntity<Integer> response = quizservice.calculateResult(id, responses);
            logger.info("Result calculated successfully for quiz ID: {}", id);
            return response;
        } catch (Exception e) {
            logger.error("Error calculating result for quiz ID: {}", id, e);
            return ResponseEntity.status(500).body(0);
        }
    }

}