package org.ahmedukamel.eduai.service.teacher;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.dto.teacher.AddTeacherRequestITeacherRegistrationRequest;
import org.ahmedukamel.eduai.mapper.profile.TeacherProfileResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.saver.teacher.ITeacherRegistrationRequestSaver;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherManagementService implements ITeacherManagementService {
    private final ITeacherRegistrationRequestSaver iTeacherRegistrationRequestSaver;
    private final TeacherProfileResponseMapper teacherProfileResponseMapper;

    @Override
    public Object addTeacher(Object object) {
        AddTeacherRequestITeacherRegistrationRequest request = (AddTeacherRequestITeacherRegistrationRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Teacher teacher = iTeacherRegistrationRequestSaver.apply(request, school);

        TeacherProfileResponse response = teacherProfileResponseMapper.apply(teacher);
        String message = "Successful teacher registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }
}