package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDENTS")
public class Student extends User {
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

//    @ManyToOne
//    private Parent parent;

    @OneToMany(mappedBy = "student")
    private Collection<ExamResult> examResults;

    @OneToMany(mappedBy = "student")
    private Collection<Interaction> interactions;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;
}