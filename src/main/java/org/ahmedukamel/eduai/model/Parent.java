package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARENTS", uniqueConstraints = {
        @UniqueConstraint(name = "PARENT_PHONE_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"code", "number"})
})
public class Parent extends User {
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "countryCode", column = @Column(name = "code", nullable = false)),
            @AttributeOverride(name = "nationalNumber", column = @Column(name = "number", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ParentDetail> parentDetails = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    private Collection<Interaction> interactions;

    @OneToMany(mappedBy = "parent")
    private Set<Student> students;
}