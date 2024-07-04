package org.ahmedukamel.eduai.service.employee_training_attendance;

public interface IEmployeeTrainingAttendanceService {
    Object addEmployeeTrainingAttendance(Object object);

    Object updateEmployeeTrainingAttendance(Long id,Object object);

    Object softDeleteEmployeeTrainingAttendance(Long id);

    Object getEmployeeTrainingAttendance(Long id);

    Object getAllEmployeeTrainingAttendance(int pageSize, int pageNumber);
}
