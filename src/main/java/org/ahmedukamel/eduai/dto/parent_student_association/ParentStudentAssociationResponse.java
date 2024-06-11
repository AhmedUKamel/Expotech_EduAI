package org.ahmedukamel.eduai.dto.parent_student_association;

import java.time.LocalDateTime;

public record ParentStudentAssociationResponse(
        Long parentId,

        Long studentId,

        String type,

        LocalDateTime createdAt
) {
}