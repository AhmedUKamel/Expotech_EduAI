package org.ahmedukamel.eduai.service.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.department.CreateDepartmentRequest;
import org.ahmedukamel.eduai.dto.department.DepartmentResponse;
import org.ahmedukamel.eduai.dto.department.UpdateDepartmentRequest;
import org.ahmedukamel.eduai.mapper.department.DepartmentResponseMapper;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.saver.department.DepartmentSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.department.DepartmentUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentManagementService implements IDepartmentManagementService {
    private final DepartmentResponseMapper departmentResponseMapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentUpdater departmentUpdater;
    private final SchoolRepository schoolRepository;
    private final DepartmentSaver departmentSaver;

    @Override
    public Object createDepartment(Object object) {
        CreateDepartmentRequest request = (CreateDepartmentRequest) object;

        Department savedDepartment = departmentSaver.apply(request);

        DepartmentResponse response = departmentResponseMapper.apply(savedDepartment);
        String message = "Department created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateDepartment(Integer id, Object object) {
        Department department = DatabaseService.get(departmentRepository::findById, id, Department.class);
        UpdateDepartmentRequest request = (UpdateDepartmentRequest) object;

        Department updatedDepartment = departmentUpdater.apply(department, request);

        DepartmentResponse response = departmentResponseMapper.apply(updatedDepartment);
        String message = "Department updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteDepartment(Integer id) {
        Department department = DatabaseService.get(departmentRepository::findById, id, Department.class);

        departmentRepository.delete(department);

        String message = "Department deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getDepartment(Integer id) {
        Department department = DatabaseService.get(departmentRepository::findById, id, Department.class);

        DepartmentResponse response = departmentResponseMapper.apply(department);
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllDepartments(long pageSize, long pageNumber) {
        List<Department> departments = departmentRepository
                .selectDepartmentsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<DepartmentResponse> response = departments
                .stream()
                .map(departmentResponseMapper)
                .toList();
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getSchoolDepartments(Integer schoolId, long pageSize, long pageNumber) {
        School school = DatabaseService.get(schoolRepository::findById, schoolId, School.class);

        List<Department> departments = departmentRepository
                .selectSchoolDepartmentsWithPagination(school, pageSize, pageSize * (pageNumber - 1));

        List<DepartmentResponse> response = departments
                .stream()
                .map(departmentResponseMapper)
                .toList();
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}