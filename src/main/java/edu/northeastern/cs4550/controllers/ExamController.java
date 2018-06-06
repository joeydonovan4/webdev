package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.Exam;
import edu.northeastern.cs4550.services.widget.exam.IExamService;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamController {

    @Autowired
    private IExamService examService;

    @GetMapping
    public ResponseEntity<List<Exam>> findAllExams() {
        List<Exam> exams = examService.findAllExams();
        return ResponseEntity.ok(exams);
    }
}
