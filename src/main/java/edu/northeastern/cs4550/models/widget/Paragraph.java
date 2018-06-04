package edu.northeastern.cs4550.models.widget;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Paragraph")
public class Paragraph extends Widget {
}
