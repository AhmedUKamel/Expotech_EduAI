package org.ahmedukamel.eduai.dto.profile;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;

import java.time.LocalDate;

public record ParentProfileResponse(
        String username,

        String email,

        String picture,

        boolean hasPicture,

        String nid,

        Gender gender,

        Nationality nationality,

        Integer regionId,

        String number,

        String firstName,

        String lastName,

        String about,

        String phoneNumber,

        String occupation
) {
}