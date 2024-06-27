package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.AttachmentFormat;
import org.ahmedukamel.eduai.model.enumeration.AttachmentType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATTACHMENTS")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String filename;

    @Column(nullable = false, updatable = false)
    private String extension;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AttachmentType type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AttachmentFormat format;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;
}