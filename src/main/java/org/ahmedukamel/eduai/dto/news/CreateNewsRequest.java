package org.ahmedukamel.eduai.dto.news;

import jakarta.validation.constraints.NotBlank;
import org.ahmedukamel.eduai.annotation.ExistEmployee;
import org.ahmedukamel.eduai.annotation.ExistTeacher;

public record CreateNewsRequest(

    @NotBlank
    String content,
    Long teacherId,
    Long employeeId
) {
}
