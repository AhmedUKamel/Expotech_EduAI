package org.ahmedukamel.eduai.dto.department;

public record AllLanguagesDepartmentResponse(
        Integer id,

        String name_en,

        String name_ar,

        String name_fr,

        String description_en,

        String description_ar,

        String description_fr,

        String abbreviation_en,

        String abbreviation_ar,

        String abbreviation_fr
) {
}