package org.ahmedukamel.eduai.dto.room.office;

public record OfficeResponse(
        Long roomId,

        Long officeId,

        String buildingNumber,

        String roomNumber,

        Integer floorNumber,

        String type,

        String status,

        String category,

        Integer maxCapacity,

        Integer schoolId,

        String labName,

        String officeType
) {
}