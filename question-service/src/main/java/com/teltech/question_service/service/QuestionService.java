package com.teltech.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teltech.question_service.model.Question;
import com.teltech.question_service.model.QuestionWrapper;
import com.teltech.question_service.model.Response;
import com.teltech.question_service.repository.QuestionRepo;
import com.teltech.question_service.util.QuestionException;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionrepo;

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public List<Question> getAllQuestions() {
        try {
            logger.info("Fetching all questions from the repository.");
            List<Question> allQuestions = questionrepo.findAll();
            logger.info("Retrieved {} questions from the repository.", allQuestions.size());
            return allQuestions;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all questions.", e);
            throw new QuestionException("Error fetching all questions: " + e.getMessage());
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            logger.info("Fetching questions for category: {}", category);
            List<Question> questions = questionrepo.findByCategory(category);
            logger.info("Retrieved {} questions for category: {}", questions.size(), category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching questions for category: {}", category, e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            logger.info("Adding a new question: {}", question);
            questionrepo.save(question);
            logger.info("Question added successfully.");
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding question: {}", question, e);
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            logger.info("Updating question with ID: {}", question.getId());
            questionrepo.save(question);
            logger.info("Question updated successfully.");
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating question with ID: {}", question.getId(), e);
            return new ResponseEntity<>("Failed to update question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuestion(Question question) {
        try {
            logger.info("Deleting question with ID: {}", question.getId());
            questionrepo.delete(question);
            logger.info("Question deleted successfully.");
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting question with ID: {}", question.getId(), e);
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numOfQuestions) {
        try {
            logger.info("Fetching {} random questions for category: {}", numOfQuestions, categoryName);
            List<Integer> questions = questionrepo.findRandomQuestionsByCategory(categoryName, numOfQuestions);
            logger.info("Retrieved random question IDs: {}", questions);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching random questions for category: {}", categoryName, e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
        try {
            logger.info("Fetching questions by IDs: {}", questionIds);
            List<QuestionWrapper> wrappers = new ArrayList<>();
            for (Integer id : questionIds) {
                Optional<Question> questionOpt = questionrepo.findById(id);
                if (questionOpt.isPresent()) {
                    Question q = questionOpt.get();
                    QuestionWrapper qw = new QuestionWrapper();
                    qw.setId(q.getId());
                    qw.setQuestion_title(q.getQuestion_title());
                    qw.setOption1(q.getOption1());
                    qw.setOption2(q.getOption2());
                    qw.setOption3(q.getOption3());
                    qw.setOption4(q.getOption4());
                    wrappers.add(qw);
                } else {
                    logger.warn("Question with ID: {} not found.", id);
                }
            }
            logger.info("Successfully wrapped {} questions.", wrappers.size());
            return new ResponseEntity<>(wrappers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching questions by IDs: {}", questionIds, e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        try {
            logger.info("Calculating score for responses: {}", responses);
            int right = 0;
            for (Response response : responses) {
                Optional<Question> questionOpt = questionrepo.findById(response.getId());
                if (questionOpt.isPresent() && response.getResponse().equals(questionOpt.get().getRight_answer())) {
                    right++;
                }
            }
            logger.info("Calculated score: {}", right);
            return new ResponseEntity<>(right, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error calculating score for responses: {}", responses, e);
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}