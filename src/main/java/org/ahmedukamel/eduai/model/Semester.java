package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.SemesterName;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SEMESTERS")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private SemesterName name;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate startDate;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate endDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;
}