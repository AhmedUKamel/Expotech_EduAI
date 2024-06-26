package org.ahmedukamel.eduai.dto.event;

public record EventResponse(
        Long id,

        Integer schoolId,

        String title_en,

        String description_en,

        String title_ar,

        String description_ar,

        String title_fr,

        String description_fr

) {
}