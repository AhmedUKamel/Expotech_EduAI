package org.ahmedukamel.eduai.service.teacher;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.dto.teacher.AddTeacherRequest;
import org.ahmedukamel.eduai.mapper.profile.TeacherProfileResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.saver.teacher.ITeacherRegistrationRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherManagementService implements ITeacherManagementService {
    private final ITeacherRegistrationRequestSaver iTeacherRegistrationRequestSaver;
    private final TeacherProfileResponseMapper teacherProfileResponseMapper;
    private final TeacherRepository teacherRepository;

    @Override
    public Object addTeacher(Object object) {
        AddTeacherRequest request = (AddTeacherRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Teacher teacher = iTeacherRegistrationRequestSaver.apply(request, school);

        TeacherProfileResponse response = teacherProfileResponseMapper.apply(teacher);

        String message = "Teacher added successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteTeacher(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Teacher teacher = DatabaseService.get(teacherRepository::findByIdAndSchool_Id,
                id, school.getId(), Teacher.class);

        try {
            teacherRepository.delete(teacher);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Teacher is associated with other records and can't be deleted.", exception);
        }

        String message = "Teacher deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getTeacher(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        Teacher teacher = DatabaseService.get(teacherRepository::findByIdAndSchool_Id,
                id, school.getId(), Teacher.class);

        TeacherProfileResponse response = teacherProfileResponseMapper.apply(teacher);
        String message = "Teacher retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllTeachers(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Teacher> teachers = teacherRepository.findAllBySchool_Id(school.getId(), pageable);

        Page<TeacherProfileResponse> response = teachers.map(teacherProfileResponseMapper);
        String message = "All teachers retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}