package org.ahmedukamel.eduai.dto.room;

import java.time.LocalDateTime;

public record RoomResponse(
        Long roomId,

        String buildingNumber,

        String roomNumber,

        Integer floorNumber,

        Integer maxCapacity,

        Integer schoolId,

        String type,

        String status,

        String category,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}