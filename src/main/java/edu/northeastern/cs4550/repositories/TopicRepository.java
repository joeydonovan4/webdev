package edu.northeastern.cs4550.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

    List<Topic> findByLessonId(int id);
}
