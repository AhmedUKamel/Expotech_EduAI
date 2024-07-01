package org.ahmedukamel.eduai.service.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.auth.EmployeeRegistrationRequest;
import org.ahmedukamel.eduai.dto.auth.ParentRegistrationRequest;
import org.ahmedukamel.eduai.dto.auth.StudentRegistrationRequest;
import org.ahmedukamel.eduai.dto.auth.TeacherRegistrationRequest;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.dto.profile.ParentProfileResponse;
import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.ParentProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.StudentProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.TeacherProfileResponseMapper;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.saver.employee.EmployeeRegistrationRequestSaver;
import org.ahmedukamel.eduai.saver.teacher.ITeacherRegistrationRequestSaver;
import org.ahmedukamel.eduai.saver.parent.IParentRegistrationRequestSaver;
import org.ahmedukamel.eduai.saver.student.IStudentRegistrationRequestSaver;
import org.ahmedukamel.eduai.service.access_token.AccessTokenService;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final EmployeeRegistrationRequestSaver employeeRegistrationRequestSaver;
    private final ITeacherRegistrationRequestSaver iTeacherRegistrationRequestSaver;
    private final IStudentRegistrationRequestSaver iStudentRegistrationRequestSaver;
    private final IParentRegistrationRequestSaver iParentRegistrationRequestSaver;
    private final EmployeeProfileResponseMapper employeeProfileResponseMapper;
    private final StudentProfileResponseMapper studentProfileResponseMapper;
    private final TeacherProfileResponseMapper teacherProfileResponseMapper;
    private final ParentProfileResponseMapper parentProfileResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final SchoolRepository schoolRepository;

    @Override
    public Object registerStudent(Object object) {
        StudentRegistrationRequest request = (StudentRegistrationRequest) object;
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Student savedStudent = iStudentRegistrationRequestSaver.apply(request, school);

        StudentProfileResponse response = studentProfileResponseMapper.apply(savedStudent);
        String message = "Successful student registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object registerParent(Object object) {
        ParentRegistrationRequest request = (ParentRegistrationRequest) object;
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Parent savedParent = iParentRegistrationRequestSaver.apply(request, school);

        ParentProfileResponse response = parentProfileResponseMapper.apply(savedParent);
        String message = "Successful parent registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object registerTeacher(Object object) {
        TeacherRegistrationRequest request = (TeacherRegistrationRequest) object;
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Teacher teacher = iTeacherRegistrationRequestSaver.apply(request, school);

        TeacherProfileResponse response = teacherProfileResponseMapper.apply(teacher);
        String message = "Successful teacher registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object registerEmployee(Object object) {
        EmployeeRegistrationRequest request = (EmployeeRegistrationRequest) object;
        Employee employee = employeeRegistrationRequestSaver.apply(request);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(employee);
        String message = "Successful employee registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (Objects.nonNull(authentication) &&
            authentication.isAuthenticated() &&
            authentication.getPrincipal() instanceof User user) {

            AccessToken accessToken = accessTokenService.create(user);

            String response = accessToken.getToken();
            String message = "Successful user login.";

            return new ApiResponse(true, message, response);
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

    @Override
    public Object logoutUser() {
        return null;
    }
}