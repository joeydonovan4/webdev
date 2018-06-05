package edu.northeastern.cs4550.models.widget.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "questionType",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EssayQuestion.class, name = "Essay"),
        @JsonSubTypes.Type(value = FillInTheBlanksQuestion.class, name = "FillInTheBlanks"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MultipleChoice"),
        @JsonSubTypes.Type(value = TrueOrFalseQuestion.class, name = "TrueOrFalse")})
public abstract class ExamQuestion extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String instructions;

    private int points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Exam exam;
}
