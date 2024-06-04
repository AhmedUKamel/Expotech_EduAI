package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "student")
    private Collection<ExamResult> examResults;

    @OneToMany(mappedBy = "student")
    private Collection<Interaction> interactions;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;
}