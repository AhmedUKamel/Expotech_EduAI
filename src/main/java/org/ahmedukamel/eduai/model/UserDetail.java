package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ahmedukamel.eduai.model.embeddable.Name;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "USER_DETAILS")
@IdClass(value = UserDetail.UserDetailId.class)
public class UserDetail {
    @Id
    @ManyToOne
    private User user;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "first", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "last", column = @Column(name = "last_name", nullable = false))
    })
    private Name name;

    @Column(nullable = false)
    private String about;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailId implements Serializable {
        private User user;
        private Language language;
    }
}