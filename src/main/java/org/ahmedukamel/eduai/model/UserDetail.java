package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailId implements Serializable {
        private User user;
        private Language language;
    }

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<TransactionHistory> transaction;
}