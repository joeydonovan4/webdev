package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.Course;
import edu.northeastern.cs4550.models.Module;
import edu.northeastern.cs4550.services.ICourseService;
import edu.northeastern.cs4550.services.IModuleService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IModuleService moduleService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable(value = "id") int id) {
        Course deletedCourse = courseService.deleteCourse(id);
        return ResponseEntity.ok(deletedCourse);
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAllCourses() {
        List<Course> allCourses = courseService.findAllCourses();
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable(value = "id") int id) {
        Course course = courseService.findCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable(value = "id") int id,
                                               @Valid @RequestBody Course course) {
        if (id != course.getId()) {
            return ResponseEntity.badRequest().body("ID in path does not match ID in course object");
        }
        Course updatedCourse = courseService.updateCourse(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @PostMapping("/{id}/modules")
    public ResponseEntity<Module> createModule(@PathVariable(value = "id") int courseId,
                                               @Valid @RequestBody Module module) {
        Module createdModule = moduleService.createModule(courseId, module);
        return ResponseEntity.ok(createdModule);
    }

    @GetMapping("/{id}/modules")
    public ResponseEntity<List<Module>> findAllModulesForCourse(
            @PathVariable(value = "id") int courseId) {
        List<Module> courseModules = moduleService.findAllModulesForCourse(courseId);
        return ResponseEntity.ok(courseModules);
    }
}
