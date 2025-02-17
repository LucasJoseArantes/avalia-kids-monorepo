package com.avaliakids.questions_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliakids.questions_service.model.Question;
import com.avaliakids.questions_service.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/questions")
@Tag(name = "Questions", description = "Endpoints para gerenciamento de perguntas")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{classLevel}")
    @Operation(summary = "Buscar perguntas por nível de classe", description = "Retorna uma lista de perguntas filtradas pelo nível da turma.")
    public ResponseEntity<List<Question>> getQuestionsByClassLevel(@PathVariable String classLevel) {
        List<Question> questions = questionService.getQuestionsByClassLevel(classLevel);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/add")
    @Operation(summary = "Adicionar uma nova pergunta", description = "Cria e salva uma nova pergunta no banco de dados.")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }
}
