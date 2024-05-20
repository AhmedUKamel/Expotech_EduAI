package org.ahmedukamel.eduai.dto.section;

public record CreateSectionRequest(
        String name,

        String number,

        String roomNumber,

        Integer classId,

        Long userId
) {
}