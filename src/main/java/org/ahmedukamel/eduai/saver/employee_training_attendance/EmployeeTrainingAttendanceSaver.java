package org.ahmedukamel.eduai.saver.employee_training_attendance;

import org.ahmedukamel.eduai.dto.employee_training_attendance.CreateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.EmployeeTrainingAttendance;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.EmployeeTrainingAttendanceRepository;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class EmployeeTrainingAttendanceSaver implements Function<CreateEmployeeTrainingAttendanceRequest, EmployeeTrainingAttendance> {
    private final EmployeeRepository employeeRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository;

    public EmployeeTrainingAttendanceSaver(EmployeeRepository employeeRepository, TrainingProgramRepository trainingProgramRepository, EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.employeeTrainingAttendanceRepository = employeeTrainingAttendanceRepository;
    }

    @Override
    public EmployeeTrainingAttendance apply(CreateEmployeeTrainingAttendanceRequest request) {
        EmployeeTrainingAttendance employeeTrainingAttendance =  EmployeeTrainingAttendance.builder()
                .employee(DatabaseService.get(employeeRepository::findById, request.employeeId(), Employee.class))
                .trainingProgram(trainingProgramRepository.findById(request.trainingProgramId()).get())
                .status(request.status())
                .absenceReason(request.absenceReason())
                .date(request.date())
                .build();
        employeeTrainingAttendanceRepository.save(employeeTrainingAttendance);
        return employeeTrainingAttendance;
    }
}
