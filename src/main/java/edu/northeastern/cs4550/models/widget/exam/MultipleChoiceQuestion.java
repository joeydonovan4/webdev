package edu.northeastern.cs4550.models.widget.exam;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("MultipleChoice")
public class MultipleChoiceQuestion extends ExamQuestion {

    @ElementCollection
    @CollectionTable(
            name = "question_choices",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id")
    )
    private List<String> choices;
}
