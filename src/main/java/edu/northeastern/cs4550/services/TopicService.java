package edu.northeastern.cs4550.services;

import org.springframework.stereotype.Service;

import java.util.List;

import edu.northeastern.cs4550.models.Topic;

@Service
public class TopicService implements ITopicService {

    @Override
    public Topic createTopic(int lessonId, Topic topic) {
        return null;
    }

    @Override
    public Topic deleteTopic(int id) {
        return null;
    }

    @Override
    public List<Topic> findAllTopics() {
        return null;
    }

    @Override
    public Topic findTopicById(int id) {
        return null;
    }

    @Override
    public List<Topic> findAllTopicsForLesson(int lessonId) {
        return null;
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return null;
    }
}
