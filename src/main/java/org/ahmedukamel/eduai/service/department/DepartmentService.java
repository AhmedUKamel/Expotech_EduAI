package org.ahmedukamel.eduai.service.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.department.LocalizedDepartmentResponse;
import org.ahmedukamel.eduai.mapper.department.LocalizedDepartmentResponseMapper;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final LocalizedDepartmentResponseMapper localizedDepartmentResponseMapper;
    private final DepartmentRepository departmentRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Object getDepartment(Integer id) {
        Department department = DatabaseService.get(departmentRepository::findById, id, Department.class);

        LocalizedDepartmentResponse response = localizedDepartmentResponseMapper.apply(department);
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllDepartments(long pageSize, long pageNumber) {
        List<Department> departments = departmentRepository
                .selectDepartmentsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<LocalizedDepartmentResponse> response = departments
                .stream()
                .map(localizedDepartmentResponseMapper)
                .toList();
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getSchoolDepartments(Integer schoolId, long pageSize, long pageNumber) {
        School school = DatabaseService.get(schoolRepository::findById, schoolId, School.class);


        List<Department> departments = departmentRepository
                .selectSchoolDepartmentsWithPagination(school, pageSize, pageSize * (pageNumber - 1));

        List<LocalizedDepartmentResponse> response = departments
                .stream()
                .map(localizedDepartmentResponseMapper)
                .toList();
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}