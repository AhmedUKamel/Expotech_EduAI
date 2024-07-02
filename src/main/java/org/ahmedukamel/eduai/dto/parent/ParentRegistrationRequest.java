package org.ahmedukamel.eduai.dto.parent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.ahmedukamel.eduai.annotation.ExistRegion;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.annotation.UniquePhone;
import org.ahmedukamel.eduai.annotation.UserUnique;
import org.ahmedukamel.eduai.constant.RegexConstants;
import org.ahmedukamel.eduai.annotation.enumeration.UniquePhoneConstraint;
import org.ahmedukamel.eduai.annotation.enumeration.UserUniqueConstraint;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.time.LocalDate;

public record ParentRegistrationRequest(
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
        @UserUnique(constraint = UserUniqueConstraint.NID)
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
        @UniquePhone(entity = UniquePhoneConstraint.PARENT)
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
        String occupation_fr,

        @NotNull
        @ExistSchool
        Integer schoolId
) implements IParentRegistrationRequest {
}