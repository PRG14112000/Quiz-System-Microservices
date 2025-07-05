package com.quiz.services.impl;

import com.quiz.entities.Quiz;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import com.quiz.repositories.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

// Make this annotation so that later we can autowire it 
@Service
public class QuizServiceImpl implements QuizService{

    private QuizRepository quizRepository;

    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        //Fetch all the quizzes
        List<Quiz> quizzes = quizRepository.findAll();

        // set questions to each quizzes
        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

        return newQuizList;
    }

    @Override
    public Quiz get(Long id){
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz Not Found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}