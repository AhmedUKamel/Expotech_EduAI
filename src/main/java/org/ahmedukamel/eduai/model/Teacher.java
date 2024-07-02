package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Qualification;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Qualification qualification;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "teacher")
    private Collection<Interaction> interactions;
}