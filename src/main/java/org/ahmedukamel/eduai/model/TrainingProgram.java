package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.TargetAudience;
import org.ahmedukamel.eduai.model.enumeration.TrainingStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "TRAINING_PROGRAMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Date startDate;

    @Column(nullable = false, updatable = false)
    private Date endDate;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<TrainingProgramDetails> trainingProgramDetails = new HashSet<>();

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<TeacherTrainingAttendance> teacherAttendances;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<EmployeeTrainingAttendance> employeeAttendances;

    @Column(nullable = false, columnDefinition = "boolean default false" )
    private boolean deleted;

    @Column(nullable = false)
    private TargetAudience targetAudience;

    @Column(nullable = false)
    private LocalDateTime schedule;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private TrainingStatus trainingStatus;

    @Column(nullable = false)
    private int Cost;
}
