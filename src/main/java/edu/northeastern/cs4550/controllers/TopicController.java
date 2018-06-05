package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.widget.Widget;
import edu.northeastern.cs4550.services.IWidgetService;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private IWidgetService widgetService;

    @PostMapping("/{id}/widgets")
    public ResponseEntity<List<Widget>> saveAllWidgets(@PathVariable(value = "id") int topicId,
            @Valid @RequestBody WidgetList widgetList) {
        List<Widget> savedWidgets = widgetService.saveWidgets(topicId, widgetList.getWidgets());
        return ResponseEntity.ok(savedWidgets);
    }
}

class WidgetList {
    @Getter @Setter
    private List<Widget> widgets;
}
