package org.ahmedukamel.eduai.dto.profile;

import java.time.LocalDate;

public record StudentProfileResponse(
        String username,

        String email,

        String picture,

        boolean hasPicture,

        String nid,

        String gender,

        String nationality,

        String religion,

        LocalDate birthDate,

        Integer regionId,

        String firstName,

        String lastName,

        String about
) {
}