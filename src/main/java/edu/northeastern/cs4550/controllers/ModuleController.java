package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.Module;
import edu.northeastern.cs4550.services.IModuleService;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    private IModuleService moduleService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Module> deleteModule(@PathVariable(value = "id") int id) {
        Module deletedModule = moduleService.deleteModule(id);
        return ResponseEntity.ok(deletedModule);
    }

    @GetMapping
    public ResponseEntity<List<Module>> findAllModules() {
        List<Module> allModules = moduleService.findAllModules();
        return ResponseEntity.ok(allModules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> findModuleById(@PathVariable(value = "id") int id) {
        Module module = moduleService.findModuleById(id);
        return ResponseEntity.ok(module);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateModule(@PathVariable(value = "id") int id,
                                               @Valid @RequestBody Module module) {
        if (id != module.getId()) {
            return ResponseEntity.badRequest().body("ID in path does not match ID in module object");
        }
        Module updatedModule = moduleService.updateModule(module);
        return ResponseEntity.ok(updatedModule);
    }
}
