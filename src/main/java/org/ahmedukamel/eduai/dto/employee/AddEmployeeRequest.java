package org.ahmedukamel.eduai.dto.employee;

import jakarta.validation.constraints.*;
import org.ahmedukamel.eduai.annotation.*;
import org.ahmedukamel.eduai.annotation.enumeration.UniquePhoneConstraint;
import org.ahmedukamel.eduai.annotation.enumeration.UserUniqueConstraint;
import org.ahmedukamel.eduai.constant.RegexConstants;
import org.ahmedukamel.eduai.dto.user.IUserRegistrationRequest;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.time.LocalDate;

public record AddEmployeeRequest(
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
        @UniquePhone(entity = UniquePhoneConstraint.TEACHER)
        String number,

        @NotBlank
        String firstName_en,

        @NotBlank
        String firstName_ar,

        @NotBlank
        String firstName_fr,

        @NotBlank
        String lastName_en,

        @NotBlank
        String lastName_ar,

        @NotBlank
        String lastName_fr,

        @NotBlank
        String about_en,

        @NotBlank
        String about_ar,

        @NotBlank
        String about_fr,

        @NotNull
        @ExistPosition
        Integer positionId,

        @NotNull
        @Min(value = 0)
        Double salary,

        @NotNull
        LocalDate hireDate
) implements IUserRegistrationRequest {
}