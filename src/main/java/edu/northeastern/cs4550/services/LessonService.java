package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Course;
import edu.northeastern.cs4550.models.Lesson;
import edu.northeastern.cs4550.models.Module;
import edu.northeastern.cs4550.repositories.CourseRepository;
import edu.northeastern.cs4550.repositories.LessonRepository;
import edu.northeastern.cs4550.repositories.ModuleRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Lesson createLesson(int courseId, int moduleId, Lesson lesson) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException(Course.class, "id", Integer.toString(courseId)));
        return moduleRepository.findById(moduleId).map(module -> {
            module.setCourse(course);
            lesson.setModule(module);
            return lessonRepository.save(lesson);
        }).orElseThrow(() ->
                new ResourceNotFoundException(Module.class, "id", Integer.toString(moduleId)));
    }

    @Override
    public Lesson deleteLesson(int id) {
        Lesson existingLesson = findLessonById(id);
        lessonRepository.delete(existingLesson);
        return existingLesson;
    }

    @Override
    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findLessonById(int id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (!lesson.isPresent()) {
            throw new ResourceNotFoundException(Lesson.class, "id", Integer.toString(id));
        }
        return lesson.get();
    }

    @Override
    public List<Lesson> findAllLessonsForCourseModule(int courseId, int moduleId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException(Course.class, "id", Integer.toString(courseId));
        }
        if (!moduleRepository.existsById(moduleId)) {
            throw new ResourceNotFoundException(Module.class, "id", Integer.toString(moduleId));
        }
        return lessonRepository.findByModuleId(moduleId);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Lesson existingLesson = findLessonById(lesson.getId());
        existingLesson.setTitle(lesson.getTitle());
        existingLesson.setModule(lesson.getModule());
        lessonRepository.save(existingLesson);
        return existingLesson;
    }
}
