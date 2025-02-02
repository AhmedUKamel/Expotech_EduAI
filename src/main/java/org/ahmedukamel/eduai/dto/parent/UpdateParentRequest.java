package org.ahmedukamel.eduai.dto.parent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistRegion;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.time.LocalDate;

public record UpdateParentRequest(
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

        @NotBlank
        String occupation_en,

        @NotBlank
        String occupation_ar,

        @NotBlank
        String occupation_fr
) {
}