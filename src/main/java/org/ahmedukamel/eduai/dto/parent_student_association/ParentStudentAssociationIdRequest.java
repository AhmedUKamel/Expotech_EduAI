package org.ahmedukamel.eduai.dto.parent_student_association;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistParent;
import org.ahmedukamel.eduai.annotation.ExistStudent;

public record ParentStudentAssociationIdRequest(
        @NotNull
        @ExistParent
        Long parentId,

        @NotNull
        @ExistStudent
        Long studentId
) {
}