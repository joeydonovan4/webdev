package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.Lesson;
import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.models.Widget;
import edu.northeastern.cs4550.repositories.TopicRepository;
import edu.northeastern.cs4550.repositories.WidgetRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class WidgetService implements IWidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ILessonService lessonService;

    @Override
    public Widget createWidget(int lessonId, int topicId, Widget widget) {
        Lesson lesson = lessonService.findLessonById(lessonId);
        return topicRepository.findById(topicId).map(topic -> {
            topic.setLesson(lesson);
            widget.setTopic(topic);
            return widgetRepository.save(widget);
        }).orElseThrow(() ->
                new ResourceNotFoundException(Topic.class, "id", Integer.toString(topicId)));
    }

    @Override
    public Widget deleteWidget(int id) {
        Widget existingWidget = findWidgetById(id);
        widgetRepository.delete(existingWidget);
        return existingWidget;
    }

    @Override
    public List<Widget> findAllWidgets() {
        return widgetRepository.findAll();
    }

    @Override
    public Widget findWidgetById(int id) {
        Optional<Widget> widget = widgetRepository.findById(id);
        if (!widget.isPresent()) {
            throw new ResourceNotFoundException(Widget.class, "id", Integer.toString(id));
        }
        return widget.get();
    }

    @Override
    public List<Widget> findAllWidgetsForLessonTopic(int lessonId, int topicId) {
        lessonService.findLessonById(lessonId);
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException(Topic.class, "id", Integer.toString(topicId));
        }
        return widgetRepository.findByTopicId(topicId);
    }

    @Override
    public Widget updateWidget(Widget widget) {
        Widget existingWidget = findWidgetById(widget.getId());
        existingWidget.setName(widget.getName());
        existingWidget.setOrder(widget.getOrder());
        existingWidget.setText(widget.getText());
        existingWidget.setClassName(widget.getClassName());
        existingWidget.setStyle(widget.getStyle());
        existingWidget.setWidth(widget.getWidth());
        existingWidget.setHeight(widget.getHeight());
        existingWidget.setSize(widget.getSize());
        existingWidget.setHref(widget.getHref());
        existingWidget.setSrc(widget.getSrc());
        existingWidget.setListItems(widget.getListItems());
        existingWidget.setListType(widget.getListType());
        existingWidget.setTopic(widget.getTopic());
        widgetRepository.save(existingWidget);
        return existingWidget;
    }
}
