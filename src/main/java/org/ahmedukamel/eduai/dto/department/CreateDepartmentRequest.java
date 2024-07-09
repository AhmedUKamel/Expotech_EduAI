package org.ahmedukamel.eduai.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistEmployee;
import org.ahmedukamel.eduai.model.enumeration.EmployeeRole;

import java.util.Set;

public record CreateDepartmentRequest(
        @NotBlank
        String name_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String name_fr,

        @NotBlank
        String description_en,

        @NotBlank
        String description_ar,

        @NotBlank
        String description_fr,

        @NotBlank
        String abbreviation_en,

        @NotBlank
        String abbreviation_ar,

        @NotBlank
        String abbreviation_fr,

        @NotNull
        @ExistEmployee
        Long headId,

        @NotNull
        Set<EmployeeRole> roles
) {
}