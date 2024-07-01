package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @ManyToOne
    private Parent parent;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "student")
    private Collection<ExamResult> examResults;

    @OneToMany(mappedBy = "student")
    private Collection<Interaction> interactions;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;
}