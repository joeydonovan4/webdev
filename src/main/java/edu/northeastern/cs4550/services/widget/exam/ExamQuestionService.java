package edu.northeastern.cs4550.services.widget.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.models.widget.exam.Exam;
import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;
import edu.northeastern.cs4550.repositories.widget.exam.ExamQuestionRepository;
import edu.northeastern.cs4550.repositories.widget.exam.ExamRepository;
import edu.northeastern.cs4550.services.ITopicService;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class ExamQuestionService implements IExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ITopicService topicService;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public ExamQuestion createExamQuestion(int topicId, int examId, ExamQuestion examQuestion) {
        Topic topic = topicService.findTopicById(topicId);
        return examRepository.findById(examId).map(exam -> {
            exam.setTopic(topic);
            examQuestion.setExam(exam);
            return examQuestionRepository.save(examQuestion);
        }).orElseThrow(() ->
                new ResourceNotFoundException(Exam.class, "id", Integer.toString(examId)));
    }

    @Override
    public ExamQuestion deleteExamQuestion(int id) {
        ExamQuestion existingQuestion = findExamQuestionById(id);
        examQuestionRepository.delete(existingQuestion);
        return existingQuestion;
    }

    @Override
    public List<ExamQuestion> findAllExamQuestions() {
        return examQuestionRepository.findAll();
    }

    @Override
    public ExamQuestion findExamQuestionById(int id) {
        Optional<ExamQuestion> examQuestion = examQuestionRepository.findById(id);
        if (!examQuestion.isPresent()) {
            throw new ResourceNotFoundException(ExamQuestion.class, "id", Integer.toString(id));
        }
        return examQuestion.get();
    }

    @Override
    public List<ExamQuestion> findAllExamQuestionsForTopicExam(int topicId, int examId) {
        topicService.findTopicById(topicId);
        if (!examRepository.existsById(examId)) {
            throw new ResourceNotFoundException(Exam.class, "id", Integer.toString(examId));
        }
        return examQuestionRepository.findByExamId(examId);
    }
}
