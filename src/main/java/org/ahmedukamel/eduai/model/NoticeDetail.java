package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTICE_DETAILS")
@IdClass(value = NoticeDetail.NoticeDetailId.class)
public class NoticeDetail {
    @Id
    @ManyToOne
    private Notice notice;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeDetailId implements Serializable {
        private Notice notice;
        private Language language;
    }
}