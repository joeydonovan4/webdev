package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Course;
import edu.northeastern.cs4550.repositories.CourseRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course deleteCourse(int id) {
        Course existingCourse = findCourseById(id);
        courseRepository.delete(existingCourse);
        return existingCourse;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            throw new ResourceNotFoundException(Course.class, "id", Integer.toString(id));
        }
        return course.get();
    }

    @Override
    public Course updateCourse(Course course) {
        Course existingCourse = findCourseById(course.getId());
        existingCourse.setTitle(course.getTitle());
        return courseRepository.save(existingCourse);
    }
}
