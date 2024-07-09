package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String avatarUrl;
    private String title;
    private String description;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}