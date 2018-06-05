package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.models.widget.Assignment;
import edu.northeastern.cs4550.repositories.widget.AssignmentRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class AssignmentService implements IAssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ITopicService topicService;

    @Override
    public List<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment updateAssignment(int topicId, int assignmentId, Assignment assignment) {
        Topic topic = topicService.findTopicById(topicId);
        return assignmentRepository.findById(assignment.getId()).map(existingAssignment -> {
            existingAssignment.setName(assignment.getName());
            existingAssignment.setOrder(assignment.getOrder());
            existingAssignment.setText(assignment.getText());
            existingAssignment.setStyle(assignment.getStyle());
            existingAssignment.setWidth(assignment.getWidth());
            existingAssignment.setHeight(assignment.getHeight());
            existingAssignment.setTopic(topic);

            existingAssignment.setDescription(assignment.getDescription());
            existingAssignment.setFile(assignment.getFile());
            existingAssignment.setLink(assignment.getLink());
            existingAssignment.setTitle(assignment.getTitle());
            assignmentRepository.save(existingAssignment);
            return existingAssignment;
        }).orElseThrow(() ->
                new ResourceNotFoundException(
                        Assignment.class, "id", Integer.toString(assignment.getId())));
    }
}
