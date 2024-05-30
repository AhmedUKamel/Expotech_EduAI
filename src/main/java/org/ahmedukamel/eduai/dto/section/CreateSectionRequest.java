package org.ahmedukamel.eduai.dto.section;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistStudent;

import java.util.Collection;

public record CreateSectionRequest(
        @NotBlank
        String name,

        @NotBlank
        String number,

        @NotNull
        Long classroomId,

        @NotNull
        @ExistStudent
        Collection<Long> studentsId
) {
}