package edu.northeastern.cs4550.repositories.widget;

import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs4550.models.widget.Assignment;

@Transactional
public interface AssignmentRepository extends BaseWidgetRepository<Assignment> {}
