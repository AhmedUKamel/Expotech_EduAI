package org.ahmedukamel.eduai.dto.event;

import lombok.Builder;

@Builder
public record EventResponse(
        Long id,

        Integer schoolId,

        Long creatorId,

        String title_en,

        String description_en,

        String title_ar,

        String description_ar,

        String title_fr,

        String description_fr,

        String fileName,

        String fileExtension,

        String fileFormat,

        String startDate,

        String endDate

) {
}