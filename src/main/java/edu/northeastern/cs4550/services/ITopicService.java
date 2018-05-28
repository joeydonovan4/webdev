package edu.northeastern.cs4550.services;

import java.util.List;

import edu.northeastern.cs4550.models.Topic;

public interface ITopicService {

    Topic createTopic(int lessonId, Topic topic);

    Topic deleteTopic(int id);

    List<Topic> findAllTopics();

    Topic findTopicById(int id);

    List<Topic> findAllTopicsForLesson(int lessonId);

    Topic updateTopic(Topic topic);
}
