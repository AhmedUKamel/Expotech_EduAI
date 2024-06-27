package org.ahmedukamel.eduai.dto.bus;

import java.time.LocalDateTime;

public record BusResponse(
        Long busId,

        String busNumber,

        String driverName,

        String driverNumber,

        Integer busCapacity,

        String route,

        Integer schoolId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}