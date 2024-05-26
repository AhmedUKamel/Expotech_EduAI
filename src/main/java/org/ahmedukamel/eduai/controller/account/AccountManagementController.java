package org.ahmedukamel.eduai.controller.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.ahmedukamel.eduai.constant.RegexConstants;
import org.ahmedukamel.eduai.service.account.AccountManagementService;
import org.ahmedukamel.eduai.service.account.IAccountManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/my-account")
public class AccountManagementController {
    private final IAccountManagementService service;

    public AccountManagementController(AccountManagementService service) {
        this.service = service;
    }

    @GetMapping(value = "profile")
    private ResponseEntity<?> getProfile() {
        return ResponseEntity.ok().body(service.getProfile());
    }

    @PostMapping(value = "change-password")
    private ResponseEntity<?> changePassword(@NotBlank @RequestParam(value = "password") String password,
                                             @Pattern(regexp = RegexConstants.PASSWORD) @NotBlank @RequestParam(value = "newPassword") String newPassword) {
        return ResponseEntity.accepted().body(service.changePassword(password, newPassword));
    }
}