package org.ahmedukamel.eduai.service.student_activity;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.student_activity.CreateStudentActivityRequest;
import org.ahmedukamel.eduai.dto.student_activity.StudentActivityResponse;
import org.ahmedukamel.eduai.dto.student_activity.UpdateStudentActivityRequest;
import org.ahmedukamel.eduai.mapper.student_activity.StudentActivityResponseMapper;
import org.ahmedukamel.eduai.model.StudentActivity;
import org.ahmedukamel.eduai.repository.StudentActivityRepository;
import org.ahmedukamel.eduai.saver.student_activity.StudentActivitySaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.student_activity.StudentActivityUpdater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentActivityManagementService implements IStudentActivityManagementService {
    private final StudentActivityResponseMapper studentActivityResponseMapper;
    private final StudentActivityRepository studentActivityRepository;
    private final StudentActivityUpdater studentActivityUpdater;
    private final StudentActivitySaver studentActivitySaver;

    @Override
    public Object createStudentActivity(Object object) {
        CreateStudentActivityRequest request = (CreateStudentActivityRequest) object;

        StudentActivity savedStudentActivity = studentActivitySaver.apply(request);

        StudentActivityResponse response = studentActivityResponseMapper.apply(savedStudentActivity);
        String message = "Student activity created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateStudentActivity(Long id, Object object) {
        StudentActivity studentActivity = DatabaseService.get(studentActivityRepository::findById, id, StudentActivity.class);
        UpdateStudentActivityRequest request = (UpdateStudentActivityRequest) object;

        StudentActivity updatedStudentActivity = studentActivityUpdater.apply(studentActivity, request);

        StudentActivityResponse response = studentActivityResponseMapper.apply(updatedStudentActivity);
        String message = "Student activity updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteStudentActivity(Long id) {
        StudentActivity studentActivity = DatabaseService.get(studentActivityRepository::findById, id, StudentActivity.class);

        studentActivityRepository.delete(studentActivity);

        String message = "Student activity deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getStudentActivity(Long id) {
        StudentActivity studentActivity = DatabaseService.get(studentActivityRepository::findById, id, StudentActivity.class);

        StudentActivityResponse response = studentActivityResponseMapper.apply(studentActivity);
        String message = "Student activity retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllStudentActivities(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<StudentActivity> studentActivities = studentActivityRepository
                .findAll(pageable);

        Page<StudentActivityResponse> response = studentActivities
                .map(studentActivityResponseMapper);
        String message = "Student activities retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}