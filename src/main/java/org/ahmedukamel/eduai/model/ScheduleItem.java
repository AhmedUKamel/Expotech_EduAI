package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.WeekDay;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCHEDULE_ITEMS", uniqueConstraints = {
        @UniqueConstraint(name = "SCHEDULE_ITEM_DAY_START_TIME_CLASSROOM_UNIQUE_CONSTRAINT", columnNames = {"day", "start_time", "classroom"})})
public class ScheduleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private WeekDay day;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, updatable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false, updatable = false)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column
    private boolean deleted = false;
}
