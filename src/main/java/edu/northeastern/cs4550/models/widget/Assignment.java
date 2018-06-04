package edu.northeastern.cs4550.models.widget;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Assignment")
public class Assignment extends Widget {

    @Column(nullable = false)
    private String title;

    private String description;

    private String link;

    private byte[] file;
}
