package edu.northeastern.cs4550.services;

import java.util.List;

import edu.northeastern.cs4550.models.Lesson;

public interface ILessonService {

    Lesson createLesson(int courseId, int moduleId, Lesson lesson);

    Lesson deleteLesson(int id);

    List<Lesson> findAllLessons();

    Lesson findLessonById(int id);

    List<Lesson> findAllLessonsForModule(int moduleId);

    Lesson updateLesson(Lesson lesson);
}
