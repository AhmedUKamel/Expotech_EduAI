package org.ahmedukamel.eduai.dto.section;

public record UpdateSectionRequest(
        String name,

        String number,

        String roomNumber,

        Long userId
) {
}