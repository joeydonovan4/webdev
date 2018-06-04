package edu.northeastern.cs4550.models.widget;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Image")
public class Image extends Widget {

    @Column(nullable = false)
    private String src;
}
