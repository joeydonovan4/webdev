package edu.northeastern.cs4550.services;

import java.util.List;

import edu.northeastern.cs4550.models.Course;

public interface ICourseService {

    Course createCourse(Course course);

    Course deleteCourse(int id);

    List<Course> findAllCourses();

    Course findCourseById(int id);

    Course updateCourse(Course course);
}
