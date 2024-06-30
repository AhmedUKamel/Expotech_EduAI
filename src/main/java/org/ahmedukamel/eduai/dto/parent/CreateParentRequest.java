package org.ahmedukamel.eduai.dto.parent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Religion;


import java.util.Date;

public record CreateParentRequest(
        @NotNull
        Long userId,
        @NotBlank
        String countryCode,
        @NotBlank
        String nationalNumber,
        @NotBlank
        String fullName,
        @NotNull
        Integer age,
        @NotNull
        Gender gender,
        @NotNull
        Integer numberOfChildren,
        @NotBlank
        String occupation,
        @NotNull
        Religion religion,
        @NotNull
        Date dateOfBirth,
        @NotBlank
        String address,
        @NotBlank
        String email
) {}
