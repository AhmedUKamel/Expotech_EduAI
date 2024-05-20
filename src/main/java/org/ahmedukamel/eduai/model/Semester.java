package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SEMESTERS", uniqueConstraints = {
        @UniqueConstraint(name = "SEMESTER_NAME_UNIQUE_CONSTRAINT", columnNames = "name")
})
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate start;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate end;
}