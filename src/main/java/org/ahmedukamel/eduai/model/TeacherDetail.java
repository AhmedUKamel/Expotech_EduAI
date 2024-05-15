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
@Table(name = "TEACHER_DETAILS")
@IdClass(value = TeacherDetail.TeacherDetailId.class)
public class TeacherDetail {
    @Id
    @ManyToOne
    private Teacher teacher;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String qualification;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherDetailId implements Serializable {
        private Teacher teacher;
        private Language language;
    }
}