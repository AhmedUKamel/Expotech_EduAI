package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.StudyLevel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSES", uniqueConstraints = {
        @UniqueConstraint(name = "COURSE_CODE_UNIQUE_CONSTRAINT", columnNames = {"code", "school_id"})
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String code;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private StudyLevel level;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "COURSES_PREREQUISITES",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private Set<Course> prerequisites = new HashSet<>();

    @ManyToMany(mappedBy = "prerequisites")
    private Set<Course> requiredBy = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CourseDetails> courseDetails = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<CourseRegistration> courseRegistrations = new HashSet<>();
}