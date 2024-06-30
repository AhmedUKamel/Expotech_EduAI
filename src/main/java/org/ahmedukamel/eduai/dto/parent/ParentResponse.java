package org.ahmedukamel.eduai.dto.parent;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.util.Date;

public record ParentResponse(
        Long id,
        String fullName,
        Integer age,
        Gender gender,
        Integer numberOfChildren,
        String occupation,
        Religion religion,
        Date dateOfBirth,
        String address,
        String email
) {
}
