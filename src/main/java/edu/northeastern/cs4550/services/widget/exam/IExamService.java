package edu.northeastern.cs4550.services.widget.exam;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.Exam;

public interface IExamService {

    List<Exam> findAllExams();
}
