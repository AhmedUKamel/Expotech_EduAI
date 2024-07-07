package org.ahmedukamel.eduai.service.employee_training_attendance;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.employee_training_attendance.CreateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.dto.employee_training_attendance.EmployeeTrainingAttendanceResponse;
import org.ahmedukamel.eduai.dto.employee_training_attendance.UpdateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.mapper.employee_training_attendance.EmployeeTrainingAttendanceMapper;
import org.ahmedukamel.eduai.model.EmployeeTrainingAttendance;
import org.ahmedukamel.eduai.repository.EmployeeTrainingAttendanceRepository;
import org.ahmedukamel.eduai.saver.employee_training_attendance.EmployeeTrainingAttendanceSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.training_program.attendance.EmployeeTrainingAttendanceUpdater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeTrainingAttendanceService implements  IEmployeeTrainingAttendanceService {
    private final EmployeeTrainingAttendanceSaver employeeTrainingAttendanceSaver;
    private final EmployeeTrainingAttendanceMapper employeeTrainingAttendanceMapper;
    private final EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository;
    private final EmployeeTrainingAttendanceUpdater employeeTrainingAttendanceUpdater;
    public EmployeeTrainingAttendanceService(EmployeeTrainingAttendanceSaver employeeTrainingAttendanceSaver, EmployeeTrainingAttendanceMapper employeeTrainingAttendanceMapper, EmployeeTrainingAttendanceRepository employeeTrainingAttendanceRepository, EmployeeTrainingAttendanceUpdater employeeTrainingAttendanceUpdater) {
        this.employeeTrainingAttendanceSaver = employeeTrainingAttendanceSaver;
        this.employeeTrainingAttendanceMapper = employeeTrainingAttendanceMapper;
        this.employeeTrainingAttendanceRepository = employeeTrainingAttendanceRepository;
        this.employeeTrainingAttendanceUpdater = employeeTrainingAttendanceUpdater;
    }

    @Override
    public Object addEmployeeTrainingAttendance(Object object) {
        CreateEmployeeTrainingAttendanceRequest request = (CreateEmployeeTrainingAttendanceRequest) object;
        EmployeeTrainingAttendance employeeTrainingAttendance = employeeTrainingAttendanceSaver.apply(request);
        EmployeeTrainingAttendanceResponse response = employeeTrainingAttendanceMapper.apply(employeeTrainingAttendance);
        String message = "Employee training attendance added successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateEmployeeTrainingAttendance(Long id, Object object) {
        EmployeeTrainingAttendance employeeTrainingAttendance = DatabaseService.get(employeeTrainingAttendanceRepository::findById,
                id, EmployeeTrainingAttendance.class);
        UpdateEmployeeTrainingAttendanceRequest request = (UpdateEmployeeTrainingAttendanceRequest) object;
        EmployeeTrainingAttendance updatedEmployeeTrainingAttendance = employeeTrainingAttendanceUpdater.apply(employeeTrainingAttendance, request);
        EmployeeTrainingAttendanceResponse response = employeeTrainingAttendanceMapper.apply(updatedEmployeeTrainingAttendance);
        String message = "Employee training attendance updated successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object softDeleteEmployeeTrainingAttendance(Long id) {

        EmployeeTrainingAttendance employeeTrainingAttendance = DatabaseService.get(employeeTrainingAttendanceRepository::findById,
                id, EmployeeTrainingAttendance.class);

            employeeTrainingAttendance.setDeleted(true);
            employeeTrainingAttendanceRepository.save(employeeTrainingAttendance);

        String message = "Employee training attendance deleted successfully.";
        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getEmployeeTrainingAttendance(Long id) {
        EmployeeTrainingAttendance employeeTrainingAttendance = DatabaseService.get(employeeTrainingAttendanceRepository::findById,
                id,EmployeeTrainingAttendance.class);
        if (!employeeTrainingAttendance.isDeleted()){
            EmployeeTrainingAttendanceResponse response = employeeTrainingAttendanceMapper.apply(employeeTrainingAttendance);
            String message = "Employee training attendance retrieved successfully.";
            return new ApiResponse(true, message, response);
        }

        String message = "Employee training attendance not found.";
        return new ApiResponse(false, message, "");
    }

    @Override
    public Object getAllEmployeeTrainingAttendance(int pageSize, int pageNumber) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        Page<EmployeeTrainingAttendance> employeeTrainingAttendances = employeeTrainingAttendanceRepository.findAll(pageable);

        List<EmployeeTrainingAttendance> filteredList = employeeTrainingAttendances
                .stream()
                .filter(employeeTrainingAttendance -> !employeeTrainingAttendance.isDeleted())
                .collect(Collectors.toList());

        Page<EmployeeTrainingAttendance> filteredPage = new PageImpl<>(filteredList, pageable, filteredList.size());

        Page<EmployeeTrainingAttendanceResponse> employeeTrainingAttendanceResponses = filteredPage.map(employeeTrainingAttendanceMapper);
        String message = "All employee training attendance retrieved successfully.";
        return new ApiResponse(true, message, employeeTrainingAttendanceResponses);
    }
}
