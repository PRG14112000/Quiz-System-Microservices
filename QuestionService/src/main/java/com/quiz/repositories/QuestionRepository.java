package com.quiz.repositories;

import com.quiz.entities.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>
{
//    So this is the custom finder method, so follow a rule write findBy as it is and QuizId should be a field of Question class
    List<Question> findByQuizId(Long quizId);
}
