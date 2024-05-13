package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STUDENTS", uniqueConstraints = {
        @UniqueConstraint(name = "STUDENT_NID_UNIQUE_CONSTRAINT", columnNames = "nid"),
        @UniqueConstraint(name = "STUDENT_BIRTH_DATE_UNIQUE_CONSTRAINT", columnNames = "birth_date")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String nid;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;
}