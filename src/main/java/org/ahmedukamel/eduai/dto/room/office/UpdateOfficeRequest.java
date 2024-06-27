package org.ahmedukamel.eduai.dto.room.office;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.dto.room.special.IUpdateSpecialRoomRequest;
import org.ahmedukamel.eduai.model.enumeration.OfficeType;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;

public record UpdateOfficeRequest(
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
        String officeName,

        @NotNull
        OfficeType officeType
) implements IUpdateSpecialRoomRequest {
}