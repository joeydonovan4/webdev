package edu.northeastern.cs4550.models.exam;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("TrueOrFalse")
public class TrueOrFalseQuestion extends ExamQuestion {

    @Column(nullable = false)
    private boolean isTrue;
}
