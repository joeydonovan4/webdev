package edu.northeastern.cs4550.services;


import java.util.List;

import edu.northeastern.cs4550.models.Module;

public interface IModuleService {

    Module createModule(Module module);

    Module deleteModule(int id);

    List<Module> findAllModules();

    Module findModuleById(int id);

    List<Module> findAllModulesForCourse(int courseId);

    Module updateModule(Module module);
}
