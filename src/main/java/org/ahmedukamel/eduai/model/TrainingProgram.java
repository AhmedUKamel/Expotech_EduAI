package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String title;
    @Column(nullable = false, updatable = false)
    private String description;
    @Column(nullable = false, updatable = false)
    private Date startDate;
    @Column(nullable = false, updatable = false)
    private Date endDate;
    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TrainingProgramDetails> trainingProgramDetails = new HashSet<>();

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<TeacherTrainingAttendance> teacherAttendances;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<EmployeeTrainingAttendance> employeeAttendances;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false, updatable = false)
    private School school;
}
