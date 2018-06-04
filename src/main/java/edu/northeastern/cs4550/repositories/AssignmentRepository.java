package edu.northeastern.cs4550.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs4550.models.widget.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {}
