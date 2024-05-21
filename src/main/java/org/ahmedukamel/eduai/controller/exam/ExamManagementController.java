package org.ahmedukamel.eduai.controller.exam;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.exam.CreateExamRequest;
import org.ahmedukamel.eduai.dto.exam.UpdateExamRequest;
import org.ahmedukamel.eduai.service.exam.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/exam")
public class ExamManagementController {
    private final IExamManagementService service;

    public ExamManagementController(ExamManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createExam(@Valid @RequestBody CreateExamRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/exam"))
                .body(service.createExam(request));
    }

    @PutMapping(value = "{examId}")
    public ResponseEntity<?> updateExam(@Min(value = 1) @PathVariable(value = "examId") Long id,
                                        @Valid @RequestBody UpdateExamRequest request) {
        return ResponseEntity.accepted().body(service.updateExam(id, request));
    }

    @DeleteMapping(value = "{examId}")
    public ResponseEntity<?> deleteExam(@Min(value = 1) @PathVariable(value = "examId") Long id) {
        return ResponseEntity.accepted().body(service.deleteExam(id));
    }

    @GetMapping(value = "{examId}")
    public ResponseEntity<?> getExam(@Min(value = 1) @PathVariable(value = "examId") Long id) {
        return ResponseEntity.ok().body(service.getExam(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllExams(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                         @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllExams(pageSize, pageNumber));
    }
}