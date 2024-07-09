package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.language.ILanguage;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSE_DETAILS")
@IdClass(CourseDetails.CourseDetailsId.class)
public class CourseDetails implements ILanguage {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDetailsId {
        private Course course;
        private Language language;
    }
}