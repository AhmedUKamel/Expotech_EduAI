package org.ahmedukamel.eduai.service.attendance;

public interface IAttendanceManagementService {
    Object recordAttendance(Object object);

    Object getAttendance(Long id);

    Object getAllAttendances(int pageSize, int pageNumber);

    Object getAttendancesByStudent(Long id, int pageSize, int pageNumber);

    Object getAttendancesBySection(Integer id, int pageSize, int pageNumber);
}