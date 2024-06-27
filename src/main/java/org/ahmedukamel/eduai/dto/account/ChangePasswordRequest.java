package org.ahmedukamel.eduai.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.ahmedukamel.eduai.constant.RegexConstants;

public record ChangePasswordRequest(
        @NotBlank
        String password,

        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD)
        String newPassword
) {
}