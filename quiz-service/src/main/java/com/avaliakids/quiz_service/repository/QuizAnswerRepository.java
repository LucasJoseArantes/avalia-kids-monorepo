package com.avaliakids.quiz_service.repository;

import com.avaliakids.quiz_service.model.QuizAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizAnswerRepository extends MongoRepository<QuizAnswer, String> {
    boolean existsByStudentIdAndQuestionId(String studentId, String questionId);
    List<QuizAnswer> findByStudentId(String studentId);
}

