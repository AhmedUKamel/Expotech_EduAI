package org.ahmedukamel.eduai.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistParent;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.util.Date;

public record CreateStudentRequest(
        @NotBlank
        Long id,
        @NotBlank
        @ExistParent
        long parentId,
        @NotNull
        String name,
        @NotNull
        int age,
        @NotNull
        Gender gender,
        @NotNull
        String fatherName,
        @NotNull
        String motherName,
        @NotNull
        Date dateOfBirth,
        @NotNull
        Religion religion,
        @NotNull
        String address,
        @NotNull
        String email,
        @NotNull
        String mobile
) {
}
