package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRAINING_PROGRAM_DETAILS")
@IdClass(value = TrainingProgramDetails.TrainingProgramDetailsId.class)
public class TrainingProgramDetails {
    @Id
    @ManyToOne
    private TrainingProgram trainingProgram;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrainingProgramDetailsId implements Serializable {
        private TrainingProgram trainingProgram;
        private Language language;
    }

}
