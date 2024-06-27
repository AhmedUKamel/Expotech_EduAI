package org.ahmedukamel.eduai.dto.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistEmployee;
import org.ahmedukamel.eduai.annotation.ExistPosition;

import java.time.LocalDate;

public record AssignPositionToEmployeeRequest(
        @ExistEmployee
        Long employeeId,

        @ExistPosition
        Integer positionId,

        @NotNull
        @Min(value = 0)
        Double salary,

        @NotNull
        LocalDate hireDate
) {
}