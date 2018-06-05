package edu.northeastern.cs4550.services.widget.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.Exam;
import edu.northeastern.cs4550.repositories.widget.exam.ExamRepository;

@Service
public class ExamService implements IExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> findAllExams() {
        return examRepository.findAll();
    }
}
