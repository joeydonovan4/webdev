package edu.northeastern.cs4550.repositories.widget.exam;

import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;

@Transactional
public interface ExamQuestionRepository extends BaseExamQuestionRepository<ExamQuestion> {
}
