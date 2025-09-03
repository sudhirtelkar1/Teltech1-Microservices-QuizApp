package com.teltech.quiz_service.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teltech.quiz_service.model.Question;
import com.teltech.quiz_service.model.QuestionWrapper;
import com.teltech.quiz_service.model.Quiz;
import com.teltech.quiz_service.model.Response;
import com.teltech.quiz_service.repository.QuizRepo;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    QuizRepo quizrepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            logger.info("Fetching all questions.");
            List<Question> questions = getAllQuestionsfromQuestionService();
            logger.info("Successfully fetched {} questions.", questions.size());
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all questions", e);
            return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<Question> getAllQuestionsfromQuestionService() {
        List<Question> questions = new ArrayList<>();
        final String apiUrl = "http://localhost:8081/allquestions";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            request.addHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                questions = objectMapper.readValue(jsonResponse, new TypeReference<List<Question>>() {});
            }
        } catch (Exception e) {
            logger.error("Error while calling the Question Service API to fetch questions", e);
        }
        return questions;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            logger.info("Creating quiz with title: {}, category: {}, number of questions: {}", title, category, numQ);
            List<Integer> questions = getAllQuestionsforQuizFromQuestionService(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            quizrepo.save(quiz);
            logger.info("Quiz created successfully with ID: {}", quiz.getId());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Integer> getAllQuestionsforQuizFromQuestionService(String category, int numQ) {
        final String apiUrl = "http://localhost:8081/generate?categoryName=" + category + "&numOfQuestions=" + numQ;
        List<Integer> questions = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            request.addHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                questions = objectMapper.readValue(jsonResponse, new TypeReference<List<Integer>>() {});
            }
        } catch (Exception e) {
            logger.error("Error while calling the Question Service API to fetch quiz questions", e);
        }
        return questions;
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromDB(Integer id) {
        try {
            logger.info("Fetching quiz with ID: {}", id);
            Quiz quiz = quizrepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
            List<Integer> questionIds = quiz.getQuestionIds();
            List<QuestionWrapper> questions = getQuestionsById(questionIds);
            logger.info("Successfully fetched {} questions for quiz ID: {}", questions.size(), id);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching quiz with ID: {}", id, e);
            return new ResponseEntity<>(new ArrayList<QuestionWrapper>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<QuestionWrapper> getQuestionsById(List<Integer> questionIds) {
        String apiUrl = "http://localhost:8081/getQuestions";
        List<QuestionWrapper> questions = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(questionIds);

            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonBody));

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonResponse = EntityUtils.toString(entity);
                questions = objectMapper.readValue(jsonResponse, new TypeReference<List<QuestionWrapper>>() {});
            }
        } catch (Exception e) {
            logger.error("Error while fetching questions by IDs: {}", questionIds, e);
        }
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            logger.info("Calculating result for quiz ID: {}", id);
            Integer score = getScore(responses);
            logger.info("Calculated score for quiz ID: {} is {}", id, score);
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error calculating result for quiz ID: {}", id, e);
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Integer getScore(List<Response> responses) {
        Integer score = 0;
        String apiUrl = "http://localhost:8081/getscore";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(responses);

            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonBody));

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonResponse = EntityUtils.toString(entity);
                score = objectMapper.readValue(jsonResponse, Integer.class);
            }
        } catch (Exception e) {
            logger.error("Error calculating score for responses", e);
        }
        return score;
    }
}