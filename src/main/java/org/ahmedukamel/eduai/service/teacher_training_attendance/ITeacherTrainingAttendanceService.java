package org.ahmedukamel.eduai.service.teacher_training_attendance;

public interface ITeacherTrainingAttendanceService {
    Object addTeacherTrainingAttendance(Object object);

    Object updateTeacherTrainingAttendance(Long id,Object object);

    Object deleteTeacherTrainingAttendance(Long id);

    Object getTeacherTrainingAttendance(Long id);

    Object getAllTeacherTrainingAttendance(int pageSize, int pageNumber);
}
