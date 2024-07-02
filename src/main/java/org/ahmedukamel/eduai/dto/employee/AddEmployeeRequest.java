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
        String name_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String name_fr,

        @NotBlank
        String about,

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