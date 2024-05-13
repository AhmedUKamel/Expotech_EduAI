package org.ahmedukamel.eduai.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.ahmedukamel.eduai.annotation.ExistRegion;
import org.ahmedukamel.eduai.annotation.StudentUniqueNid;
import org.ahmedukamel.eduai.annotation.UserUnique;
import org.ahmedukamel.eduai.constant.RegexConstants;
import org.ahmedukamel.eduai.enumeration.UserUniqueConstraint;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;

import java.time.LocalDate;

public record StudentRegistrationRequest(
        @NotBlank
        @UserUnique(constraint = UserUniqueConstraint.USERNAME)
        String username,

        @Email
        @NotBlank
        @UserUnique(constraint = UserUniqueConstraint.EMAIL)
        String email,

        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD)
        String password,

        @NotBlank
        @StudentUniqueNid
        String nid,

        @NotNull
        Gender gender,

        @NotNull
        Nationality nationality,

        @NotNull
        @ExistRegion
        Integer regionId,

        @NotNull
        LocalDate birthDate
) {
}