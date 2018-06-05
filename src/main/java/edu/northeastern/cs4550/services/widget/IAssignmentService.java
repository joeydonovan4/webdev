package edu.northeastern.cs4550.services.widget;

import java.util.List;

import edu.northeastern.cs4550.models.widget.Assignment;

public interface IAssignmentService {

    List<Assignment> findAllAssignments();

    Assignment updateAssignment(int topicId, int assignmentId, Assignment assignment);
}
