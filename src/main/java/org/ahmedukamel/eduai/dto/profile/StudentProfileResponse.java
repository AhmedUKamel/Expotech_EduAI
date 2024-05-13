package org.ahmedukamel.eduai.dto.profile;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;

import java.time.LocalDate;

public record StudentProfileResponse(
        String username,

        String email,

        String picture,

        boolean hasPicture,

        String nid,

        Gender gender,

        Nationality nationality,

        LocalDate birthDate,

        Integer regionId
) {
}