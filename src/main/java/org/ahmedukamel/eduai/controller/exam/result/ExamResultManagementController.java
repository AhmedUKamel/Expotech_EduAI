package org.ahmedukamel.eduai.controller.exam.result;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.ExistExam;
import org.ahmedukamel.eduai.annotation.ExistStudent;
import org.ahmedukamel.eduai.dto.exam.result.RecordExamResultRequest;
import org.ahmedukamel.eduai.service.exam.result.ExamResultManagementService;
import org.ahmedukamel.eduai.service.exam.result.IExamResultManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/exam-result")
public class ExamResultManagementController {
    private final IExamResultManagementService service;

    public ExamResultManagementController(ExamResultManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "record")
    public ResponseEntity<?> recordExamResult(@Valid @RequestBody RecordExamResultRequest request) {
        return ResponseEntity.created(URI.create("api/v1/exam-result/record"))
                .body(service.recordExamResult(request));
    }

    @DeleteMapping(value = "{examResultId}")
    public ResponseEntity<?> deleteExamResult(@Min(value = 1) @PathVariable(value = "examResultId") Long examResultId) {
        return ResponseEntity.accepted().body(service.deleteExamResult(examResultId));
    }

    @GetMapping(value = "{examResultId}")
    public ResponseEntity<?> getExamResult(@Min(value = 1) @PathVariable(value = "examResultId") Long examResultId) {
        return ResponseEntity.ok().body(service.getExamResult(examResultId));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getExamResults(@Min(value = 1) @RequestParam(value = "size") int pageSize,
                                            @Min(value = 1) @RequestParam(value = "page") int pageNumber) {
        return ResponseEntity.ok().body(service.getExamResults(pageSize, pageNumber));
    }

    @GetMapping(value = "by-exam")
    public ResponseEntity<?> getExamResultsByExam(@Min(value = 1) @ExistExam @RequestParam(value = "examId") Long examId,
                                                  @Min(value = 1) @RequestParam(value = "size") int pageSize,
                                                  @Min(value = 1) @RequestParam(value = "page") int pageNumber) {
        return ResponseEntity.ok().body(service.getExamResultsByExam(examId, pageSize, pageNumber));
    }

    @GetMapping(value = "by-student")
    public ResponseEntity<?> getExamResultsByStudent(@Min(value = 1) @ExistStudent @RequestParam(value = "studentId") Long studentId,
                                                     @Min(value = 1) @RequestParam(value = "size") int pageSize,
                                                     @Min(value = 1) @RequestParam(value = "page") int pageNumber) {
        return ResponseEntity.ok().body(service.getExamResultsByStudent(studentId, pageSize, pageNumber));
    }
}