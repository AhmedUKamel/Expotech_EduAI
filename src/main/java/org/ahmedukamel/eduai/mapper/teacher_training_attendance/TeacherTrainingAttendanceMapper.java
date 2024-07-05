package org.ahmedukamel.eduai.mapper.teacher_training_attendance;

import org.ahmedukamel.eduai.dto.teacher_training_attendance.TeacherTrainingAttendanceResponse;
import org.ahmedukamel.eduai.model.TeacherTrainingAttendance;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class TeacherTrainingAttendanceMapper implements Function<TeacherTrainingAttendance, TeacherTrainingAttendanceResponse> {
    @Override
    public TeacherTrainingAttendanceResponse apply(TeacherTrainingAttendance teacherTrainingAttendance) {

        return new TeacherTrainingAttendanceResponse(
                teacherTrainingAttendance.getId(),
                teacherTrainingAttendance.getTrainingProgram().getId(),
                teacherTrainingAttendance.getTeacher().getId(),
                teacherTrainingAttendance.getStatus(),
                teacherTrainingAttendance.getAbsenceReason(),
                teacherTrainingAttendance.getDate(),
                teacherTrainingAttendance.isDeleted()
        );
    }
}
