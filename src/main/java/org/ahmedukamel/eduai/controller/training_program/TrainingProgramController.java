package org.ahmedukamel.eduai.controller.training_program;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.teacher.AddTeacherRequest;
import org.ahmedukamel.eduai.dto.training_program.CreateTrainingProgramRequest;
import org.ahmedukamel.eduai.service.training_program.TrainingProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TRAINING_PROGRAM_MANAGER')")
@RequestMapping(value = "api/v1/training_program")
public class TrainingProgramController {
    private final TrainingProgramService service;

    public TrainingProgramController(TrainingProgramService service) {
        this.service = service;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addTeacher(
            @Valid @RequestBody CreateTrainingProgramRequest request) {

        return ResponseEntity.created(URI.create("api/v1/training_program/new"))
                .body(service.addTrainingProgram(request));
    }
    @GetMapping(value = "{trainingProgramId}")
    public ResponseEntity<?> getTrainingProgram(
            @Min(value = 1) @PathVariable(value = "trainingProgramId") Long id) {
        return ResponseEntity.accepted().body(service.getTrainingProgram(id));
    }
    @DeleteMapping(value = "{trainingProgramId}")
    public ResponseEntity<?> deleteTrainingProgram(
            @Min(value = 1) @PathVariable(value = "trainingProgramId") Long id) {
        return ResponseEntity.ok().body(service.deleteTrainingProgram(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllTrainingPrograms(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllTrainingProgram(pageSize, pageNumber));
    }
}
