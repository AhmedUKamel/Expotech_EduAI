package org.ahmedukamel.eduai.service.teacher_training_attendance;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.teacher_training_attendance.CreateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.dto.teacher_training_attendance.TeacherTrainingAttendanceResponse;
import org.ahmedukamel.eduai.dto.teacher_training_attendance.UpdateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.mapper.teacher_training_attendance.TeacherTrainingAttendanceMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.TeacherTrainingAttendance;
import org.ahmedukamel.eduai.repository.TeacherTrainingAttendanceRepository;
import org.ahmedukamel.eduai.saver.teacher_training_attendance.TeacherTrainingAttendanceSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.training_program.attendance.TeacherTrainingAttendanceUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherTrainingAttendanceService implements ITeacherTrainingAttendanceService{
    private final TeacherTrainingAttendanceSaver teacherTrainingAttendanceSaver;
    private final TeacherTrainingAttendanceMapper teacherTrainingAttendanceMapper;
    private final TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository;
    private final TeacherTrainingAttendanceUpdater teacherTrainingAttendanceUpdater;


    public TeacherTrainingAttendanceService(TeacherTrainingAttendanceSaver teacherTrainingAttendanceSaver,
                                            TeacherTrainingAttendanceMapper teacherTrainingAttendanceMapper,
                                            TeacherTrainingAttendanceRepository teacherTrainingAttendanceRepository, TeacherTrainingAttendanceUpdater teacherTrainingAttendanceUpdater) {
        this.teacherTrainingAttendanceSaver = teacherTrainingAttendanceSaver;
        this.teacherTrainingAttendanceMapper = teacherTrainingAttendanceMapper;
        this.teacherTrainingAttendanceRepository = teacherTrainingAttendanceRepository;
        this.teacherTrainingAttendanceUpdater = teacherTrainingAttendanceUpdater;
    }
    @Override
    public Object addTeacherTrainingAttendance(Object object) {
        CreateTeacherTrainingAttendanceRequest request = (CreateTeacherTrainingAttendanceRequest) object;
        TeacherTrainingAttendance teacherTrainingAttendance = teacherTrainingAttendanceSaver.apply(request);
        TeacherTrainingAttendanceResponse response = teacherTrainingAttendanceMapper.apply(teacherTrainingAttendance);
        String message = "Teacher training attendance added successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateTeacherTrainingAttendance(Long id, Object object) {
        TeacherTrainingAttendance teacherTrainingAttendance = DatabaseService.get(teacherTrainingAttendanceRepository::findById,
                id, TeacherTrainingAttendance.class);
        UpdateTeacherTrainingAttendanceRequest request = (UpdateTeacherTrainingAttendanceRequest) object;
        TeacherTrainingAttendance updatedTeacherTrainingAttendance = teacherTrainingAttendanceUpdater.apply(teacherTrainingAttendance, request);
        TeacherTrainingAttendanceResponse response = teacherTrainingAttendanceMapper.apply(updatedTeacherTrainingAttendance);
        String message = "Teacher training attendance updated successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteTeacherTrainingAttendance(Long id) {
        TeacherTrainingAttendance teacherTrainingAttendance = DatabaseService.get(teacherTrainingAttendanceRepository::findById,
                id, TeacherTrainingAttendance.class);
        try {
            teacherTrainingAttendanceRepository.delete(teacherTrainingAttendance);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Teacher training attendance is associated with other records and can't be deleted.", exception);
        }
        String message = "Teacher training attendance deleted successfully.";
        return new ApiResponse(true, message, "");
    }
    @Override
    public Object getTeacherTrainingAttendance(Long id) {
        TeacherTrainingAttendance teacherTrainingAttendance = DatabaseService.get(teacherTrainingAttendanceRepository::findById,
                id, TeacherTrainingAttendance.class);

        TeacherTrainingAttendanceResponse response = teacherTrainingAttendanceMapper.apply(teacherTrainingAttendance);
        String message = "Teacher training attendance retrieved successfully.";
        return new ApiResponse(true, message, response);
    }
    @Override
    public Object getAllTeacherTrainingAttendance(int pageSize, int pageNumber) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        Page<TeacherTrainingAttendance> teacherTrainingAttendances = teacherTrainingAttendanceRepository
                .findAll( pageable);

        Page<TeacherTrainingAttendanceResponse> responses = teacherTrainingAttendances
                .map(teacherTrainingAttendanceMapper);

        String message = "All teacher training attendances retrieved successfully.";
        return new ApiResponse(true, message, responses);
    }
}