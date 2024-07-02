package org.ahmedukamel.eduai.updater.training_program.attendance;

import org.ahmedukamel.eduai.dto.employee_training_attendance.UpdateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.EmployeeTrainingAttendance;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.EmployeeTrainingAttendanceRepository;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
@Component
public class EmployeeTrainingAttendanceUpdater implements BiFunction<EmployeeTrainingAttendance, UpdateEmployeeTrainingAttendanceRequest, EmployeeTrainingAttendance> {
    private final EmployeeRepository employeeRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository;
    public EmployeeTrainingAttendanceUpdater(EmployeeRepository employeeRepository, TrainingProgramRepository trainingProgramRepository, EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.employeeTrainingAttendanceRepository = employeeTrainingAttendanceRepository;
    }
    @Override
    public EmployeeTrainingAttendance apply(EmployeeTrainingAttendance employeeTrainingAttendance, UpdateEmployeeTrainingAttendanceRequest request) {
        employeeTrainingAttendance.setEmployee(DatabaseService.get(employeeRepository::findById, request.employeeId(), Employee.class));
        employeeTrainingAttendance.setTrainingProgram(DatabaseService.get(trainingProgramRepository::findById, request.trainingProgramId(), TrainingProgram.class));
        employeeTrainingAttendance.setStatus(request.status());
        employeeTrainingAttendance.setAbsenceReason(request.absenceReason());
        employeeTrainingAttendance.setDate(request.date());
        return employeeTrainingAttendanceRepository.save(employeeTrainingAttendance);
    }
}
