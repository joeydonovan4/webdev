package edu.northeastern.cs4550.repositories.widget.exam;

import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs4550.models.widget.exam.Exam;
import edu.northeastern.cs4550.repositories.widget.BaseWidgetRepository;

@Transactional
public interface ExamRepository extends BaseWidgetRepository<Exam> {
}
