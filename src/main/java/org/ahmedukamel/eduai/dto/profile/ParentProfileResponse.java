package org.ahmedukamel.eduai.dto.profile;

import java.time.LocalDate;

public record ParentProfileResponse(
        Long id,

        String username,

        String email,

        String picture,

        boolean hasPicture,

        String nid,

        String gender,

        String role,

        String nationality,

        String religion,

        Integer regionId,

        LocalDate birthDate,

        String number,

        String name,

        String about,

        String phoneNumber,

        String occupation
) {
}