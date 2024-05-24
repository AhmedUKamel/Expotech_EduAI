package org.ahmedukamel.eduai.dto.department;

public record DepartmentResponse(
        Integer id,

        Integer schoolId,

        String name_en,

        String name_ar,

        String name_fr
) {
}