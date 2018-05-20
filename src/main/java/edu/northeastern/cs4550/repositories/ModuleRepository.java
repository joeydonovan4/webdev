package edu.northeastern.cs4550.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {

    List<Module> findByCourseId(int id);
}
