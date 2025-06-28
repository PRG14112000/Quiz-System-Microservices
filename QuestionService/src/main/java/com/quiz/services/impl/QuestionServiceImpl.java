package com.quiz.services.impl;

import com.quiz.entities.Question;
import com.quiz.services.QuestionService;
import com.quiz.repositories.QuestionRepository;

import java.util.List;
import org.springframework.stereotype.Service;

// Make this annotation so that later we can autowire it 
@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> get() {
        return questionRepository.findAll();
    }

    @Override
    public Question getOne(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found !!"));
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Long quizId){
        return questionRepository.findByQuizId(quizId);
    }
}