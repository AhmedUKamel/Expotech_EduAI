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
@Table(name = "PARENT_DETAILS")
@IdClass(value = ParentDetail.ParentDetailId.class)
public class ParentDetail {
    @Id
    @ManyToOne
    private Parent parent;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String occupation;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentDetailId implements Serializable {
        private Parent parent;
        private Language language;
    }
}