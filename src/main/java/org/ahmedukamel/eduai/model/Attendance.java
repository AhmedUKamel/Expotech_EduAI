package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATTENDANCES", uniqueConstraints = {
        @UniqueConstraint(name = "ATTENDANCE_STUDENT_UNIQUE_CONSTRAINT", columnNames = {"student_id", "section_id"})
})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false, updatable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false, updatable = false)
    private Lesson lesson;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AttendanceStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AbsenceReason absenceReason;
}