package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private SemesterName name;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate start;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate end;

    @Column(nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "semester")
    private Collection<Exam> exams = new ArrayList<>();
}