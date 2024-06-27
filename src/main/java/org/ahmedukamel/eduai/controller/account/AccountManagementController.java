package org.ahmedukamel.eduai.controller.account;

import jakarta.validation.Valid;
import org.ahmedukamel.eduai.dto.account.ChangePasswordRequest;
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
    private ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.accepted().body(service.changePassword(request.password(), request.newPassword()));
    }
}