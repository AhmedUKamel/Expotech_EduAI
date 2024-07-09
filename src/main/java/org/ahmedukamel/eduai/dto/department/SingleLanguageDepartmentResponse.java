package org.ahmedukamel.eduai.dto.department;

import java.util.Set;

public record SingleLanguageDepartmentResponse(
        Integer id,

        String name,

        String description,

        String abbreviation,

        Set<String> roles
) {
}