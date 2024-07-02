package org.ahmedukamel.eduai.updater.training_program.attendance;

import org.ahmedukamel.eduai.dto.teacher_training_attendance.UpdateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.TeacherTrainingAttendance;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.repository.TeacherTrainingAttendanceRepository;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
@Component
public class TeacherTrainingAttendanceUpdater implements BiFunction<TeacherTrainingAttendance, UpdateTeacherTrainingAttendanceRequest,TeacherTrainingAttendance> {
    private final TeacherRepository teacherRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository;

    public TeacherTrainingAttendanceUpdater(TeacherRepository teacherRepository, TrainingProgramRepository trainingProgramRepository, TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository) {
        this.teacherRepository = teacherRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.teacherTrainingAttendanceRepository = teacherTrainingAttendanceRepository;
    }

    @Override
    public TeacherTrainingAttendance apply(TeacherTrainingAttendance teacherTrainingAttendance, UpdateTeacherTrainingAttendanceRequest request) {
        teacherTrainingAttendance.setTeacher(DatabaseService.get(teacherRepository::findById, request.teacherId(), Teacher.class));
        teacherTrainingAttendance.setTrainingProgram(DatabaseService.get(trainingProgramRepository::findById, request.trainingProgramId(), TrainingProgram.class));
        teacherTrainingAttendance.setStatus(request.status());
        teacherTrainingAttendance.setAbsenceReason(request.absenceReason());
        teacherTrainingAttendance.setDate(request.date());
        return teacherTrainingAttendanceRepository.save(teacherTrainingAttendance);
    }
}
