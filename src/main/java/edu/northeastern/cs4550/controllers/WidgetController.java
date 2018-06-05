package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.widget.Widget;
import edu.northeastern.cs4550.services.widget.IWidgetService;

@RestController
@RequestMapping("/api/widgets")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetController {

    @Autowired
    private IWidgetService widgetService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Widget> deleteWidget(@PathVariable(value = "id") int id) {
        Widget deletedWidget = widgetService.deleteWidget(id);
        return ResponseEntity.ok(deletedWidget);
    }

    @GetMapping
    public ResponseEntity<List<Widget>> findAllWidgets() {
        List<Widget> allWidgets = widgetService.findAllWidgets();
        return ResponseEntity.ok(allWidgets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Widget> findWidgetById(@PathVariable(value = "id") int id) {
        Widget widget = widgetService.findWidgetById(id);
        return ResponseEntity.ok(widget);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWidget(@PathVariable(value = "id") int id,
                                       @Valid @RequestBody Widget widget) {
        if (id != widget.getId()) {
            return ResponseEntity.badRequest().body("ID in path does not match ID in Widget object");
        }
        Widget updatedWidget = widgetService.updateWidget(widget);
        return ResponseEntity.ok(updatedWidget);
    }
}
