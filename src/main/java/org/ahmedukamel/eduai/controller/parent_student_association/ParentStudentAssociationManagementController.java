package org.ahmedukamel.eduai.controller.parent_student_association;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.parent_student_association.CreateParentStudentAssociationRequest;
import org.ahmedukamel.eduai.dto.parent_student_association.ParentStudentAssociationIdRequest;
import org.ahmedukamel.eduai.service.parent_student_association.IParentStudentAssociationManagementService;
import org.ahmedukamel.eduai.service.parent_student_association.ParentStudentAssociationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/parent-student-association")
public class ParentStudentAssociationManagementController {
    private final IParentStudentAssociationManagementService service;

    public ParentStudentAssociationManagementController(ParentStudentAssociationManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createParentStudentAssociation(@Valid @RequestBody CreateParentStudentAssociationRequest request) {
        return ResponseEntity.created(URI.create("api/v1/parent-student-association"))
                .body(service.createParentStudentAssociation(request));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteParentStudentAssociation(@Valid @RequestBody ParentStudentAssociationIdRequest request) {
        return ResponseEntity.accepted().body(service.deleteParentStudentAssociation(request));
    }

    @PutMapping
    public ResponseEntity<?> getParentStudentAssociation(@Valid @RequestBody ParentStudentAssociationIdRequest request) {
        return ResponseEntity.accepted().body(service.getParentStudentAssociation(request));
    }

    @GetMapping(value = "by-parent")
    public ResponseEntity<?> getParentStudentAssociationsByParent(
            @Min(value = 1) @RequestParam(value = "parentId") Long parentId,
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getParentStudentAssociationsByParent(parentId, pageSize, pageNumber));
    }

    @GetMapping(value = "by-student")
    public ResponseEntity<?> getParentStudentAssociationsByStudent(
            @Min(value = 1) @RequestParam(value = "studentId") Long studentId,
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getParentStudentAssociationsByStudent(studentId, pageSize, pageNumber));
    }
}