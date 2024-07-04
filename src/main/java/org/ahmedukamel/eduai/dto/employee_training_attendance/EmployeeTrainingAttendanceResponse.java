package org.ahmedukamel.eduai.dto.employee_training_attendance;

import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

import java.time.LocalDate;

public record EmployeeTrainingAttendanceResponse(
        Long id,

        Long employeeId,

        Long trainingProgramId,

        AttendanceStatus status,

        AbsenceReason absenceReason,

        LocalDate date,

        Boolean deleted
) {
}
