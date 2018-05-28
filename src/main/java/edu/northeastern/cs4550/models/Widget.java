package edu.northeastern.cs4550.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Widget extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "`order`", length = 10)
    private int order;

    private String text;

    private String className;

    private String style;

    private String width;

    private String height;

    private int size; // specific to Heading widgets

    private String href; // specific to Link widgets

    private String src; // specific to Image widgets

    private String listItems; // specific to List widgets

    @Enumerated(EnumType.STRING)
    private ListType listType; // specific to List widgets

    @ManyToOne
    @JsonIgnore
    private Topic topic;
}

enum ListType {
    ORDERED, UNORDERED;
}
