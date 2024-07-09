package org.ahmedukamel.eduai.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistRegion;
import org.ahmedukamel.eduai.model.enumeration.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateTeacherRequest(
        @NotBlank
        String username,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String nid,

        @NotNull
        Religion religion,

        @NotNull
        Gender gender,

        @NotNull
        Nationality nationality,

        @NotNull
        @ExistRegion
        Integer regionId,

        @NotNull
        LocalDate birthDate,

        @NotBlank
        String number,

        @NotBlank
        String name_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String name_fr,

        @NotBlank
        String about,

        @NotNull
        Qualification qualification,
        @NotNull
        LocalDateTime createdDate,

        @NotNull
        LocalDateTime updatedDate,

        @NotNull
        Subject subject
) {
}
