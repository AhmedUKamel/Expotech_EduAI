package org.ahmedukamel.eduai.dto.profile;

import java.time.LocalDate;

public record TeacherProfileResponse(
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

        LocalDate birthDate,

        Integer regionId,

        String number,

        String name,

        String about,

        String phoneNumber,

        String qualification
) {
}