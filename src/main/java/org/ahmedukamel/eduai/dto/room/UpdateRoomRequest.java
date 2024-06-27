package org.ahmedukamel.eduai.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.AllowedRoomCategories;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;

public record UpdateRoomRequest(
        @NotBlank
        String buildingNumber,

        @NotBlank
        String roomNumber,

        @NotNull
        Integer floorNumber,

        @NotNull
        RoomType type,

        @NotNull
        @AllowedRoomCategories
        RoomCategory category,

        @NotNull
        RoomStatus status,

        @NotNull
        @Min(value = 0)
        Integer maxCapacity
) {
}