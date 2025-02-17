package com.avaliakids.questions_service.service;

import com.avaliakids.questions_service.model.Question;
import com.avaliakids.questions_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestionsByClassLevel(String classLevel) {
        return questionRepository.findByClassLevel(classLevel);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}
