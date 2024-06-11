package org.ahmedukamel.eduai.dto.user_management;

import java.time.LocalDate;

public record AdminParentResponse(
        String name,

        Long parentId,

        Long userId,

        String gender,

        String religion,

        String occupation,

        String address,

        String mobile,

        String email,

        String picture,

        boolean hasPicture,

        Integer age,

        Integer childrenCount,

        LocalDate birthDate
) {
}