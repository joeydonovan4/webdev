package edu.northeastern.cs4550.models.widget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import edu.northeastern.cs4550.models.Audit;
import edu.northeastern.cs4550.models.Topic;
import edu.northeastern.cs4550.models.widget.exam.Exam;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "widget_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "widgetType",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Heading.class, name = "Heading"),
        @JsonSubTypes.Type(value = Paragraph.class, name = "Paragraph"),
        @JsonSubTypes.Type(value = Image.class, name = "Image"),
        @JsonSubTypes.Type(value = Link.class, name = "Link"),
        @JsonSubTypes.Type(value = List.class, name = "List"),
        @JsonSubTypes.Type(value = Exam.class, name = "Exam"),
        @JsonSubTypes.Type(value = Assignment.class, name = "Assignment")})
public abstract class Widget extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "`order`", length = 10, nullable = false)
    private int order;

    private String text;

    private String style;

    private String height;

    private String width;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Topic topic;
}
