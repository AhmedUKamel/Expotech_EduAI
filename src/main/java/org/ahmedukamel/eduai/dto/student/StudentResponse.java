package org.ahmedukamel.eduai.dto.student;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.util.Date;

public record StudentResponse(
        Long id,
        long parentId,
        String name,
        int age,
        Gender gender,
        String fatherName,
        String motherName,
        Date dateOfBirth,
        Religion religion,
        String address,
        String email,
        String mobile
) {
}
