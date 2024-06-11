package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.AssociationType;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@IdClass(value = ParentStudentAssociation.ParentStudentAssociationId.class)
public class ParentStudentAssociation {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, updatable = false)
    private Parent parent;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, updatable = false)
    private Student student;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AssociationType type;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentStudentAssociationId implements Serializable {
        private Parent parent;
        private Student student;

        @Override
        public String toString() {
            return "ParentStudentAssociationId(" +
                   "parentId=" + parent.getId() + ", " +
                   "studentId=" + student.getId() + ")";
        }
    }
}