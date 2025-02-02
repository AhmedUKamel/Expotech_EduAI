package org.ahmedukamel.eduai.mapper.employee_training_attendance;

import org.ahmedukamel.eduai.dto.employee_training_attendance.EmployeeTrainingAttendanceResponse;
import org.ahmedukamel.eduai.model.EmployeeTrainingAttendance;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class EmployeeTrainingAttendanceMapper implements Function<EmployeeTrainingAttendance, EmployeeTrainingAttendanceResponse> {
    @Override
    public EmployeeTrainingAttendanceResponse apply(EmployeeTrainingAttendance employeeTrainingAttendance) {
        return new EmployeeTrainingAttendanceResponse(
                employeeTrainingAttendance.getId(),
                employeeTrainingAttendance.getEmployee().getId(),
                employeeTrainingAttendance.getTrainingProgram().getId(),
                employeeTrainingAttendance.getStatus(),
                employeeTrainingAttendance.getAbsenceReason(),
                employeeTrainingAttendance.getDate(),
                employeeTrainingAttendance.isDeleted()
        );
    }
}
