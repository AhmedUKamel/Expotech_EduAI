package org.ahmedukamel.eduai.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public record NewsResponse (
        @NotNull
        Long id,
        @NotBlank
        String content,
        @NotNull
        LocalDateTime localDateTime,
        @NotNull
        Long userId
){
}
