package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

import java.time.LocalDate;

@Entity
@Table(name = "TEACHER_TRAINING_ATTENDANCES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherTrainingAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_program_id", nullable = true, updatable = false)
    private TrainingProgram trainingProgram;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true, updatable = false)
    private Teacher teacher;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AttendanceStatus status;

    @Enumerated(value = EnumType.STRING)
    private AbsenceReason absenceReason;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private LocalDate date;
}
