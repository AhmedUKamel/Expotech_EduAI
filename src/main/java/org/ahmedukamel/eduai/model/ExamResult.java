package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.ExamResultStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXAM_RESULTS")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Student student;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private ExamResultStatus status;

    @Column(nullable = false, updatable = false)
    private Float score;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime scoreDate;
}