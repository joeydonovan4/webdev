package edu.northeastern.cs4550.models.widget;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("List")
public class List extends Widget {

    @Column(nullable = false)
    private String listItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ListType listType;
}

enum ListType {
    ORDERED, UNORDERED;
}
