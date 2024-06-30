package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TEACHERS", uniqueConstraints = {
        @UniqueConstraint(name = "TEACHER_PHONE_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"code", "number"})
})
public class Teacher extends User {
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "countryCode", column = @Column(name = "code", nullable = false)),
            @AttributeOverride(name = "nationalNumber", column = @Column(name = "number", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TeacherDetail> teacherDetails = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Collection<Interaction> interactions;
}