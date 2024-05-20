package org.ahmedukamel.eduai.dto.classes;

public record UpdateClassRequest(
        String name,

        String number,

        String group,

        Integer floor
) {
}