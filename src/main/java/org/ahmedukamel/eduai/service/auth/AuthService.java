package org.ahmedukamel.eduai.service.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.auth.StudentRegistrationRequest;
import org.ahmedukamel.eduai.dto.auth.TeacherRegistrationRequest;
import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.StudentProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.TeacherProfileResponseMapper;
import org.ahmedukamel.eduai.model.AccessToken;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.saver.auth.StudentSaver;
import org.ahmedukamel.eduai.saver.auth.TeacherSaver;
import org.ahmedukamel.eduai.service.access_token.AccessTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final StudentProfileResponseMapper studentProfileResponseMapper;
    private final TeacherProfileResponseMapper teacherProfileResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final TeacherSaver teacherSaver;
    private final StudentSaver studentSaver;

    @Override
    public Object registerStudent(Object object) {
        StudentRegistrationRequest request = (StudentRegistrationRequest) object;
        Student savedStudent = studentSaver.apply(request);

        StudentProfileResponse response = studentProfileResponseMapper.apply(savedStudent);
        String message = "Successful student registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object registerParent(Object object) {
        return null;
    }

    @Override
    public Object registerTeacher(Object object) {
        TeacherRegistrationRequest request = (TeacherRegistrationRequest) object;
        Teacher teacher = teacherSaver.apply(request);

        TeacherProfileResponse response = teacherProfileResponseMapper.apply(teacher);
        String message = "Successful teacher registration, check mail inbox for activation email.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object registerEmployee(Object object) {
        return null;
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