package org.ahmedukamel.eduai.dto.room.lab;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.dto.room.special.IUpdateSpecialRoomRequest;
import org.ahmedukamel.eduai.model.enumeration.LabType;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;

public record UpdateLabRequest(
        @NotBlank
        String buildingNumber,

        @NotBlank
        String roomNumber,

        @NotNull
        Integer floorNumber,

        @NotNull
        RoomType type,

        @NotNull
        RoomStatus status,

        @NotNull
        @Min(value = 0)
        Integer maxCapacity,

        @NotBlank
        String labName,

        @NotNull
        LabType labType
) implements IUpdateSpecialRoomRequest {
}