package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.Lesson;
import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.models.Widget;
import edu.northeastern.cs4550.services.ILessonService;
import edu.northeastern.cs4550.services.ITopicService;
import edu.northeastern.cs4550.services.IWidgetService;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonController {

    @Autowired
    private ILessonService lessonService;

    @Autowired
    private IWidgetService widgetService;

    @Autowired
    private ITopicService topicService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable(value = "id") int id) {
        Lesson deletedLesson = lessonService.deleteLesson(id);
        return ResponseEntity.ok(deletedLesson);
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> findAllLessons() {
        List<Lesson> allLessons = lessonService.findAllLessons();
        return ResponseEntity.ok(allLessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> findLessonById(@PathVariable(value = "id") int id) {
        Lesson lesson = lessonService.findLessonById(id);
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLesson(@PathVariable(value = "id") int id,
                                       @Valid @RequestBody Lesson lesson) {
        if (id != lesson.getId()) {
            return ResponseEntity.badRequest().body("ID in path does not match ID in Lesson object");
        }
        Lesson updatedLesson = lessonService.updateLesson(lesson);
        return ResponseEntity.ok(updatedLesson);
    }

    @PostMapping("/{lid}/topics")
    public ResponseEntity<Topic> createTopic(@PathVariable(value = "lid") int lessonId,
                                             @Valid @RequestBody Topic topic) {
        Topic createdTopic = topicService.createTopic(lessonId, topic);
        return ResponseEntity.ok(createdTopic);
    }

    @GetMapping("/{lid}/topics")
    public ResponseEntity<List<Topic>> findAllTopicsForLesson(
            @PathVariable(value = "lid") int lessonId) {
        List<Topic> topics = topicService.findAllTopicsForLesson(lessonId);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/{lid}/topics/{tid}/widgets")
    public ResponseEntity<Widget> createWidget(@PathVariable(value = "lid") int lessonId,
                                               @PathVariable(value = "tid") int topicId,
                                               @Valid @RequestBody Widget widget) {
        Widget createdWidget = widgetService.createWidget(lessonId, topicId, widget);
        return ResponseEntity.ok(createdWidget);
    }

    @GetMapping("/{lid}/topics/{tid}/widgets")
    public ResponseEntity<List<Widget>> findAllWidgetsForLessonTopic(
            @PathVariable(value = "lid") int lessonId, @PathVariable(value = "tid") int topicId) {
        List<Widget> topicWidgets = widgetService.findAllWidgetsForLessonTopic(lessonId, topicId);
        return ResponseEntity.ok(topicWidgets);
    }
}
