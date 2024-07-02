package org.ahmedukamel.eduai.dto.teacher_training_attendance;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistTeacher;
import org.ahmedukamel.eduai.annotation.ExistTrainingProgram;
import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;


import java.time.LocalDate;

public record CreateTeacherTrainingAttendanceRequest(
        @NotNull
        @ExistTeacher
        long teacherId,
        @NotNull
        @ExistTrainingProgram
        long trainingProgramId,
        @NotNull
        AttendanceStatus status,
        @NotNull
        AbsenceReason absenceReason,
        @NotNull
        LocalDate date

) {
}
