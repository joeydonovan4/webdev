package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Lesson;
import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.repositories.TopicRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ILessonService lessonService;

    @Override
    public Topic createTopic(int lessonId, Topic topic) {
        Lesson lesson = lessonService.findLessonById(lessonId);
        topic.setLesson(lesson);
        return topicRepository.save(topic);
    }

    @Override
    public Topic deleteTopic(int id) {
        Topic existingTopic = findTopicById(id);
        topicRepository.delete(existingTopic);
        return existingTopic;
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findTopicById(int id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (!topic.isPresent()) {
            throw new ResourceNotFoundException(Topic.class, "id", Integer.toString(id));
        }
        return topic.get();
    }

    @Override
    public List<Topic> findAllTopicsForLesson(int lessonId) {
        lessonService.findLessonById(lessonId);
        return topicRepository.findByLessonId(lessonId);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        Topic existingTopic = findTopicById(topic.getId());
        existingTopic.setTitle(topic.getTitle());
        existingTopic.setLesson(topic.getLesson());
        return topicRepository.save(existingTopic);
    }
}
