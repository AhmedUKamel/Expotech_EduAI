package org.ahmedukamel.eduai.dto.classes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;

public record CreateClassRequest(
        @NotBlank
        String name,

        @NotBlank
        String number,

        @NotBlank
        String group,

        @NotNull
        @Min(value = 0)
        Integer floor,

        @NotNull
        @ExistSchool
        Integer schoolId
) {
}