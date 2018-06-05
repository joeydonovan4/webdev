package edu.northeastern.cs4550.repositories.widget;

import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs4550.models.widget.Widget;

@Transactional
public interface WidgetRepository extends BaseWidgetRepository<Widget> {

}
