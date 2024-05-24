package org.ahmedukamel.eduai.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;

public record CreateDepartmentRequest(
        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotBlank
        String name_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String name_fr
) {
}