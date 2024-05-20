package org.ahmedukamel.eduai.dto.section;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistUser;

public record UpdateSectionRequest(
        @NotBlank
        String name,

        @NotBlank
        String number,

        @NotBlank
        String roomNumber,

        @NotNull
        @ExistUser
        Long userId
) {
}