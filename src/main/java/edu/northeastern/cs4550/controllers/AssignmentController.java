package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.northeastern.cs4550.models.widget.Assignment;
import edu.northeastern.cs4550.services.IAssignmentService;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentController {

    @Autowired
    private IAssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<Assignment>> findAllAssignments() {
        List<Assignment> assignments = assignmentService.findAllAssignments();
        return ResponseEntity.ok(assignments);
    }
}
