package org.ahmedukamel.eduai.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistUser;
import org.springframework.stereotype.Component;

@Component
public record CreateNewsRequest(

    @NotBlank
    String content,
    @NotNull
    @ExistUser
    Long userId
) {
}
