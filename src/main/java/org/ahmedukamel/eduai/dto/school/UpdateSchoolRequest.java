package org.ahmedukamel.eduai.dto.school;

import org.ahmedukamel.eduai.model.enumeration.Language;

public record UpdateSchoolRequest(
        String name,

        String about,

        Language language,

        String theme
) {
}