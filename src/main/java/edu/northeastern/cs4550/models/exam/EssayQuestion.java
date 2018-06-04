package edu.northeastern.cs4550.models.exam;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Essay")
public class EssayQuestion extends ExamQuestion {

}
