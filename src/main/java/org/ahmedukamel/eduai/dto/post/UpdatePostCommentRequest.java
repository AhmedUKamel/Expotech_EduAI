package org.ahmedukamel.eduai.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistUser;

public record UpdatePostCommentRequest(

        @NotBlank
        String textContent
) {
}
