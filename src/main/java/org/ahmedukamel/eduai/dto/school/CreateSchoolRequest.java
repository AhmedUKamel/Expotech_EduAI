package org.ahmedukamel.eduai.dto.school;

import org.ahmedukamel.eduai.model.enumeration.Language;

import java.time.LocalDate;

public record CreateSchoolRequest(
        String name,

        LocalDate established,

        String about,

        Language language,

        String code,

        String theme
) {
}