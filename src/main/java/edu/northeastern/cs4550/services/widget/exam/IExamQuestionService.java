package edu.northeastern.cs4550.services.widget.exam;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;

public interface IExamQuestionService {

    ExamQuestion createExamQuestion(int topicId, int examId, ExamQuestion examQuestion);

    ExamQuestion deleteExamQuestion(int id);

    List<ExamQuestion> findAllExamQuestions();

    ExamQuestion findExamQuestionById(int id);

    List<ExamQuestion> findAllExamQuestionsForTopicExam(int topicId, int examId);
}
