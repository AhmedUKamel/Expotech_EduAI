package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSE_REGISTRATIONS")
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Semester semester;
}