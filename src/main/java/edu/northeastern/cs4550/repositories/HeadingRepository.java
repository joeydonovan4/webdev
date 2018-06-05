package edu.northeastern.cs4550.repositories;

import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs4550.models.widget.Heading;

@Transactional
public interface HeadingRepository extends BaseWidgetRepository<Heading> {
}
