package org.ahmedukamel.eduai.controller.auth.public_;

import org.ahmedukamel.eduai.service.public_.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/public/enumeration")
public class EnumerationController {
    private final IEnumerationService service;

    public EnumerationController(EnumerationService service) {
        this.service = service;
    }

    @GetMapping(value = "nationality")
    public ResponseEntity<?> getNationalities() {
        return ResponseEntity.ok().body(service.getNationalities());
    }

    @GetMapping(value = "religion")
    public ResponseEntity<?> getReligions() {
        return ResponseEntity.ok().body(service.getReligions());
    }

    @GetMapping(value = "gender")
    public ResponseEntity<?> getGenders() {
        return ResponseEntity.ok().body(service.getGenders());
    }
}