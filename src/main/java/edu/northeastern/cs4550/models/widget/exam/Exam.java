package edu.northeastern.cs4550.models.widget.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import edu.northeastern.cs4550.models.widget.Widget;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Exam")
public class Exam extends Widget {

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam", orphanRemoval = true)
    @JsonIgnore
    private List<ExamQuestion> questions;
}
