package edu.northeastern.cs4550.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Integer> {

    List<Lesson> findByModuleId(int id);
}
