package org.ahmedukamel.eduai.dto.teacher_training_attendance;

import org.ahmedukamel.eduai.model.enumeration.AbsenceReason;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

import java.time.LocalDate;

public record TeacherTrainingAttendanceResponse(

        Long id,

        Long teacherId,

        Long trainingProgramId,

        AttendanceStatus status,

        AbsenceReason absenceReason,

        LocalDate date
){
}
