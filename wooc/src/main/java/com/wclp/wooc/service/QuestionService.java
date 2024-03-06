package com.wclp.wooc.service;

import com.wclp.wooc.model.Question;
import com.wclp.wooc.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }
}
