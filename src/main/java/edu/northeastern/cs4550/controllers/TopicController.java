package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.widget.Assignment;
import edu.northeastern.cs4550.models.widget.Widget;
import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;
import edu.northeastern.cs4550.services.widget.IAssignmentService;
import edu.northeastern.cs4550.services.widget.IWidgetService;
import edu.northeastern.cs4550.services.widget.exam.IExamQuestionService;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicController {

    @Autowired
    private IWidgetService widgetService;

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @PostMapping("/{id}/widgets")
    public ResponseEntity<List<Widget>> saveAllWidgets(@PathVariable(value = "id") int topicId,
            @Valid @RequestBody WidgetList widgetList) {
        List<Widget> savedWidgets = widgetService.saveWidgets(topicId, widgetList.getWidgets());
        return ResponseEntity.ok(savedWidgets);
    }

    @PutMapping("/{tid}/assignments/{aid}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable(value = "tid") int topicId,
                                                       @PathVariable(value = "aid") int assignmentId,
                                                       @Valid @RequestBody Assignment a) {
        Assignment updatedAssignment = assignmentService.updateAssignment(topicId, assignmentId, a);
        return ResponseEntity.ok(updatedAssignment);
    }

    @PostMapping("/{tid}/exams/{eid}/questions")
    public ResponseEntity<ExamQuestion> createExamQuestion(@PathVariable(value = "tid") int topicId,
                                                           @PathVariable(value = "eid") int examId,
                                                           @Valid @RequestBody ExamQuestion question) {
        ExamQuestion examQuestion = examQuestionService.createExamQuestion(topicId, examId, question);
        return ResponseEntity.ok(examQuestion);
    }

    @GetMapping("/{tid}/exams/{eid}/questions")
    public ResponseEntity<List<ExamQuestion>> findAllExamQuestionsForTopicExam(
            @PathVariable(value = "tid") int topicId, @PathVariable(value = "eid") int examId) {
        List<ExamQuestion> examQuestions = examQuestionService.findAllExamQuestionsForTopicExam(
                topicId, examId
        );
        return ResponseEntity.ok(examQuestions);
    }
}

class WidgetList {
    @Getter @Setter
    private List<Widget> widgets;
}
