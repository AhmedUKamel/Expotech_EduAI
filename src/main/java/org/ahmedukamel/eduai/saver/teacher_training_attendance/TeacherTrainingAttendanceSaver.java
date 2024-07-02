package org.ahmedukamel.eduai.saver.teacher_training_attendance;

import org.ahmedukamel.eduai.dto.teacher_training_attendance.CreateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.TeacherTrainingAttendance;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.repository.TeacherTrainingAttendanceRepository;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class TeacherTrainingAttendanceSaver implements Function<CreateTeacherTrainingAttendanceRequest, TeacherTrainingAttendance> {
    private final TeacherRepository teacherRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository;;

    public TeacherTrainingAttendanceSaver(TeacherRepository teacherRepository, TrainingProgramRepository trainingProgramRepository, TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository) {
        this.teacherRepository = teacherRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.teacherTrainingAttendanceRepository = teacherTrainingAttendanceRepository;
    }

    @Override
    public TeacherTrainingAttendance apply(CreateTeacherTrainingAttendanceRequest request) {
        TeacherTrainingAttendance teacherTrainingAttendance =  TeacherTrainingAttendance.builder()
                .teacher(DatabaseService.get(teacherRepository::findById, request.teacherId(), Teacher.class))
                .trainingProgram(trainingProgramRepository.findById(request.trainingProgramId()).get())
                .status(request.status())
                .absenceReason(request.absenceReason())
                .date(request.date())
                .build();
        teacherTrainingAttendanceRepository.save(teacherTrainingAttendance);
        return teacherTrainingAttendance;
    }
}
