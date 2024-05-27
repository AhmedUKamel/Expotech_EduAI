package org.ahmedukamel.eduai.dto.room.classroom;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.dto.room.special.ICreateSpecialRoomRequest;
import org.ahmedukamel.eduai.model.enumeration.RoomType;
import org.ahmedukamel.eduai.model.enumeration.StudyLevel;
import org.ahmedukamel.eduai.model.enumeration.StudyStage;

public record CreateClassroomRequest(
        @NotBlank
        String buildingNumber,

        @NotBlank
        String roomNumber,

        @NotNull
        Integer floorNumber,

        @NotNull
        RoomType type,

        @NotNull
        @Min(value = 0)
        Integer maxCapacity,

        @NotNull
        @Min(value = 1)
        @ExistSchool
        Integer schoolId,

        @NotBlank
        String classroomName,

        @NotNull
        Integer classroomNumber,

        @NotNull
        StudyLevel studyLevel,

        @NotNull
        StudyStage studyStage
) implements ICreateSpecialRoomRequest {
}