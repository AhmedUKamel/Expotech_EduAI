package org.ahmedukamel.eduai.dto.profile;

import java.time.LocalDate;

public record ParentProfileResponse(
        String username,

        String email,

        String picture,

        boolean hasPicture,

        String nid,

        String gender,

        String nationality,

        String religion,

        Integer regionId,

        LocalDate birthDate,

        String number,

        String firstName,

        String lastName,

        String about,

        String phoneNumber,

        String occupation
) {
}