package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;
import edu.northeastern.cs4550.services.widget.exam.IExamQuestionService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamQuestionController {

    @Autowired
    private IExamQuestionService examQuestionService;

    @GetMapping
    public ResponseEntity<List<ExamQuestion>> findAllExamQuestions() {
        List<ExamQuestion> examQuestions = examQuestionService.findAllExamQuestions();
        return ResponseEntity.ok(examQuestions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamQuestion> deleteExamQuestion(@PathVariable(value = "id") int questionId) {
        ExamQuestion deletedQuestion = examQuestionService.deleteExamQuestion(questionId);
        return ResponseEntity.ok(deletedQuestion);
    }
}
