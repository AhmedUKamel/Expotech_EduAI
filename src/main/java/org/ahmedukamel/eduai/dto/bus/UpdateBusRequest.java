package org.ahmedukamel.eduai.dto.bus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.AllowedRoomCategories;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;

public record UpdateBusRequest(
        @NotNull
        Long busId,

        @NotBlank
        String busNumber,

        @NotBlank
        String driverName,

        @NotBlank
        String driverNumber,

        @NotNull
        @Min(0)
        Integer busCapacity,

        @NotBlank
        String route
        ) {
}