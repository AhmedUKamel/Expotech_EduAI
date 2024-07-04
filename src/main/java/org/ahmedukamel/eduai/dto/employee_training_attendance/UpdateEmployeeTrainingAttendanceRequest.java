package org.ahmedukamel.eduai.dto.employee_training_attendance;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistTrainingProgram;
import org.ahmedukamel.eduai.annotation.ExistUser;
import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

import java.time.LocalDate;
public record UpdateEmployeeTrainingAttendanceRequest(
        @NotNull
        @ExistUser
        long employeeId,
        @NotNull
        @ExistTrainingProgram
        long trainingProgramId,
        @NotNull
        AttendanceStatus status,
        @NotNull
        AbsenceReason absenceReason,
        @NotNull
        LocalDate date,
        @NotNull
        boolean deleted
) {

}
