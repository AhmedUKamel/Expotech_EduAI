package org.ahmedukamel.eduai.dto.event;

public record UpdateEventRequest(

        String title_en,

        String description_en,

        String title_ar,

        String description_ar,

        String title_fr,

        String description_fr
) {
}