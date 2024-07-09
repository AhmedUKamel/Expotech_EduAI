package org.ahmedukamel.eduai.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.springframework.stereotype.Component;

@Component
public record UpdateNewsRequest (
        @NotBlank
        String content,
        @NotNull
        @ExistSchool
        Long userId
){

}
