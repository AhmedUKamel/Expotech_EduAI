package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT_ACTIVITY_DETAILS")
@IdClass(value = StudentActivityDetail.StudentActivityDetailId.class)
public class StudentActivityDetail {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private StudentActivity studentActivity;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentActivityDetailId implements Serializable {
        private StudentActivity studentActivity;
        private Language language;
    }
}