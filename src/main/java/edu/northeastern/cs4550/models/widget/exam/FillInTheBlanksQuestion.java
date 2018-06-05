package edu.northeastern.cs4550.models.widget.exam;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("FillInTheBlanks")
public class FillInTheBlanksQuestion extends ExamQuestion {

    @Column(nullable = false)
    private String variables;
}
