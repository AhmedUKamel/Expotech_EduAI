package org.ahmedukamel.eduai.dto.room.lab;

public record LabResponse(
        Long roomId,

        Long labId,

        String buildingNumber,

        String roomNumber,

        Integer floorNumber,

        String type,

        String status,

        String category,

        Integer maxCapacity,

        Integer schoolId,

        String labName,

        String labType
) {
}