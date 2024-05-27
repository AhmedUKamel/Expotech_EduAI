package org.ahmedukamel.eduai.dto.room.classroom;

public record ClassroomResponse(
        Long roomId,

        Long classroomId,

        String buildingNumber,

        String roomNumber,

        Integer floorNumber,

        String type,

        String status,

        String category,

        Integer maxCapacity,

        Integer schoolId,

        String classroomName,

        Integer classroomNumber,

        String studyLevel,

        String studyStage
) {
}