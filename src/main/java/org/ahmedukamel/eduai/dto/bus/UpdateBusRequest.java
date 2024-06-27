package org.ahmedukamel.eduai.dto.bus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBusRequest(
        @NotBlank
        String busNumber,

        @NotBlank
        String driverName,

        @NotBlank
        String driverNumber,

        @NotNull
        @Min(value = 1)
        Integer busCapacity,

        @NotBlank
        String route
) {
}