package edu.northeastern.cs4550.repositories.widget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.widget.Widget;

@Repository
public interface BaseWidgetRepository<T extends Widget> extends JpaRepository<T,Integer> {

    List<T> findByTopicId(int topicId);
}
