package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = "USER_USERNAME_UNIQUE_CONSTRAINT", columnNames = "username"),
        @UniqueConstraint(name = "USER_EMAIL_UNIQUE_CONSTRAINT", columnNames = "email"),
        @UniqueConstraint(name = "USER_NATIONAL_ID_UNIQUE_CONSTRAINT", columnNames = "nid"),
        @UniqueConstraint(name = "USER_PICTURE_UNIQUE_CONSTRAINT", columnNames = "picture")
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private String nid;

    private String picture;

    @Column(nullable = false, columnDefinition = "bit(1) default true")
    private boolean accountNonLocked = true;

    @Column(nullable = false, columnDefinition = "bit(1) default false")
    private boolean enabled;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Nationality nationality;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime registration;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Region region;

    @OneToOne(mappedBy = "user")
    private Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserDetail> details = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Collection<Section> sections = new ArrayList<>();
}