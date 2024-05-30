package org.ahmedukamel.eduai.dto.section;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistStudent;

import java.util.Collection;

public record UpdateSectionRequest(
        @NotBlank
        String name,

        @NotBlank
        String number,

        @NotNull
        @ExistStudent
        Collection<Long> studentsId
) {
}