package org.ahmedukamel.eduai.dto.school;

import org.ahmedukamel.eduai.model.enumeration.Language;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SchoolResponse(
        Integer id,

        String name,

        LocalDate established,

        String about,

        Language language,

        String code,

        String theme,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}