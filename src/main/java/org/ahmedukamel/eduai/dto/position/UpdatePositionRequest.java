package org.ahmedukamel.eduai.dto.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistDepartment;

public record UpdatePositionRequest(
        @NotNull
        @ExistDepartment
        Integer departmentId,

        @NotBlank
        String title_en,

        @NotBlank
        String title_ar,

        @NotBlank
        String title_fr
) {
}