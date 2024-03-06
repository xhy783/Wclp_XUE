package com.wclp.wooc.controller;

import com.wclp.wooc.model.Question;
import com.wclp.wooc.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // getAllQuestions
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    // getQuestionById
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.findQuestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // createQuestion
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    // updateQuestion
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        return questionService.findQuestionById(id)
                .map(question -> {
                    question.setTitle(questionDetails.getTitle());
                    question.setOptionA(questionDetails.getOptionA());
                    question.setOptionB(questionDetails.getOptionB());
                    question.setOptionC(questionDetails.getOptionC());
                    question.setOptionD(questionDetails.getOptionD());
                    question.setCorrectAnswer(questionDetails.getCorrectAnswer());
                    Question updatedQuestion = questionService.saveQuestion(question);
                    return ResponseEntity.ok(updatedQuestion);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // deleteQuestion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok().build();
    }
}
