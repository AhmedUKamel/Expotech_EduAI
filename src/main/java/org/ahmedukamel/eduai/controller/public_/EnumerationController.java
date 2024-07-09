package org.ahmedukamel.eduai.controller.public_;

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

    @GetMapping(value = "lab-type")
    public ResponseEntity<?> getLabTypes() {
        return ResponseEntity.ok().body(service.getLabTypes());
    }

    @GetMapping(value = "office-type")
    public ResponseEntity<?> getOfficeTypes() {
        return ResponseEntity.ok().body(service.getOfficeTypes());
    }

    @GetMapping(value = "room-category")
    public ResponseEntity<?> getRoomCategories() {
        return ResponseEntity.ok().body(service.getRoomCategories());
    }

    @GetMapping(value = "room-status")
    public ResponseEntity<?> getRoomStatuses() {
        return ResponseEntity.ok().body(service.getRoomStatuses());
    }

    @GetMapping(value = "room-type")
    public ResponseEntity<?> getRoomTypes() {
        return ResponseEntity.ok().body(service.getRoomTypes());
    }

    @GetMapping(value = "study-level")
    public ResponseEntity<?> getStudyLevels() {
        return ResponseEntity.ok().body(service.getStudyLevels());
    }

    @GetMapping(value = "study-stage")
    public ResponseEntity<?> getStudyStages() {
        return ResponseEntity.ok().body(service.getStudyStages());
    }

    @GetMapping(value = "attendance-status")
    public ResponseEntity<?> getAttendanceStatus() {
        return ResponseEntity.ok().body(service.getAttendanceStatuses());
    }

    @GetMapping(value = "exam-result-status")
    public ResponseEntity<?> getExamResultStatuses() {
        return ResponseEntity.ok().body(service.getExamResultStatuses());
    }

    @GetMapping(value = "association-type")
    public ResponseEntity<?> getAssociationTypes() {
        return ResponseEntity.ok().body(service.getAssociationTypes());
    }

    @GetMapping(value = "qualification")
    public ResponseEntity<?> getQualifications() {
        return ResponseEntity.ok().body(service.getQualifications());
    }

    @GetMapping(value = "role")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok().body(service.getRoles());
    }

    @GetMapping(value = "employee-type")
    public ResponseEntity<?> getEmployeeTypes() {
        return ResponseEntity.ok().body(service.getEmployeeTypes());
    }

    @GetMapping(value = "employee-status")
    public ResponseEntity<?> getEmployeeStatuses() {
        return ResponseEntity.ok().body(service.getEmployeeStatuses());
    }

    @GetMapping(value = "subject")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok().body(service.getSubjects());
    }

    @GetMapping(value = "employee-role")
    public ResponseEntity<?> getEmployeeRoles() {
        return ResponseEntity.ok().body(service.getEmployeeRoles());
    }
}