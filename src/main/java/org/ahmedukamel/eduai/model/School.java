package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCHOOLS", uniqueConstraints = {
        @UniqueConstraint(name = "SCHOOL_NAME_UNIQUE_CONSTRAINT ", columnNames = "name"),
        @UniqueConstraint(name = "SCHOOL_CODE_UNIQUE_CONSTRAINT ", columnNames = "code")
})
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private LocalDate established;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String about;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(nullable = false, updatable = false)
    private String code;

    @Column(nullable = false)
    private String theme;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "school")
    private Collection<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private Collection<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private Collection<Notice> notices;

    @OneToMany(mappedBy = "school")
    private Set<StudentActivity> studentActivities;

    @OneToMany(mappedBy = "school")
    private Set<Bus> buses;
}