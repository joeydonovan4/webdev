package edu.northeastern.cs4550.services;

import org.springframework.stereotype.Service;

import java.util.List;

import edu.northeastern.cs4550.models.Widget;

@Service
public class WidgetService implements IWidgetService {

    @Override
    public Widget createWidget(int lessonId, int topicId, Widget widget) {
        return null;
    }

    @Override
    public Widget deleteWidget(int id) {
        return null;
    }

    @Override
    public List<Widget> findAllWidgets() {
        return null;
    }

    @Override
    public Widget findWidgetById(int id) {
        return null;
    }

    @Override
    public List<Widget> findAllWidgetsForLessonTopic(int lessonId, int topicId) {
        return null;
    }

    @Override
    public Widget updateWidget(Widget widget) {
        return null;
    }
}
