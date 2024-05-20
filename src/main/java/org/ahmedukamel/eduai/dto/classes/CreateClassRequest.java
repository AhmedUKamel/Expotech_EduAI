package org.ahmedukamel.eduai.dto.classes;

public record CreateClassRequest(
        String name,

        String number,

        String group,

        Integer floor,

        Integer schoolId
) {
}