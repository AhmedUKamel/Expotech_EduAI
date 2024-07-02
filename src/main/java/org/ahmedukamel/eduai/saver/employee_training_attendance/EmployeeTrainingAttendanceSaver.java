package org.ahmedukamel.eduai.saver.employee_training_attendance;

import org.ahmedukamel.eduai.dto.employee_training_attendance.CreateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.EmployeeTrainingAttendance;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.EmployeeTrainingAttendanceRepository;

import java.util.function.BiFunction;

public class EmployeeTrainingAttendanceSaver implements BiFunction<CreateEmployeeTrainingAttendanceRequest, TrainingProgram, EmployeeTrainingAttendance> {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository;

    public EmployeeTrainingAttendanceSaver(EmployeeRepository employeeRepository, EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeTrainingAttendanceRepository = employeeTrainingAttendanceRepository;
    }

    @Override
    public EmployeeTrainingAttendance apply(CreateEmployeeTrainingAttendanceRequest request, TrainingProgram trainingProgram) {
        EmployeeTrainingAttendance trainingAttendance =  EmployeeTrainingAttendance.builder()
                .employee(employeeRepository.findById(request.employeeId()).get())
                .trainingProgram(trainingProgram)
                .status(request.status())
                .absenceReason(request.absenceReason())
                .date(request.date())
                .build();
        employeeTrainingAttendanceRepository.save(trainingAttendance);
        return trainingAttendance;
    }
}
