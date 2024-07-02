package org.ahmedukamel.eduai.saver.teacher_training_attendance;

import org.ahmedukamel.eduai.dto.teacher_training_attendance.CreateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.TeacherTrainingAttendance;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.repository.TeacherTrainingAttendanceRepository;

import java.util.function.BiFunction;

public class TeacherTrainingAttendanceSaver implements BiFunction<CreateTeacherTrainingAttendanceRequest, TrainingProgram, TeacherTrainingAttendance> {
    private final TeacherRepository teacherRepository;
    private final TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository;;

    public TeacherTrainingAttendanceSaver(TeacherRepository teacherRepository, TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherTrainingAttendanceRepository = teacherTrainingAttendanceRepository;
    }

    @Override
    public TeacherTrainingAttendance apply(CreateTeacherTrainingAttendanceRequest request, TrainingProgram trainingProgram) {
        TeacherTrainingAttendance teacherTrainingAttendance = TeacherTrainingAttendance.builder()
                .teacher(teacherRepository.findById(request.teacherId()).get())
                .trainingProgram(trainingProgram)
                .status(request.status())
                .absenceReason(request.absenceReason())
                .date(request.date())
                .build();
        teacherTrainingAttendanceRepository.save(teacherTrainingAttendance);
        return teacherTrainingAttendance;
    }
}
