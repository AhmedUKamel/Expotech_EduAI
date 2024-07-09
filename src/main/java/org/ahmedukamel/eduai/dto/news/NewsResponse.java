package org.ahmedukamel.eduai.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NewsResponse (
        Integer id,
        @NotBlank
        String content,
        @NotNull
        LocalDateTime localDateTime,

        Long teacherId,
        Long employeeId
){
}
