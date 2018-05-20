package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Course;
import edu.northeastern.cs4550.models.Module;
import edu.northeastern.cs4550.repositories.CourseRepository;
import edu.northeastern.cs4550.repositories.ModuleRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class ModuleService implements IModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Module createModule(int courseId, Module module) {
        return courseRepository.findById(courseId).map(course -> {
            module.setCourse(course);
            return moduleRepository.save(module);
        }).orElseThrow(() ->
                new ResourceNotFoundException(Course.class, "id", Integer.toString(courseId)));
    }

    @Override
    public Module deleteModule(int id) {
        Module existingModule = findModuleById(id);
        moduleRepository.delete(existingModule);
        return existingModule;
    }

    @Override
    public List<Module> findAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findModuleById(int id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (!module.isPresent()) {
            throw new ResourceNotFoundException(Module.class, "id", Integer.toString(id));
        }
        return module.get();
    }

    @Override
    public List<Module> findAllModulesForCourse(int courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    @Override
    public Module updateModule(Module module) {
        Module existingModule = findModuleById(module.getId());
        existingModule.setTitle(module.getTitle());
        existingModule.setCourse(module.getCourse());
        moduleRepository.save(existingModule);
        return existingModule;
    }
}
