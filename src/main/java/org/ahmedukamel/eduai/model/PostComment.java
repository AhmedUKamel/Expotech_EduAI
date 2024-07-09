package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POST_COMMENTS")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false, updatable = false)
    private User creator;

    @Column
    private String file;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @Column
    private boolean deleted = false;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;
}
