package edu.northeastern.cs4550.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.widget.Widget;

@Repository
public interface WidgetRepository extends JpaRepository<Widget,Integer> {

    List<Widget> findByTopicId(int topicId);
}
