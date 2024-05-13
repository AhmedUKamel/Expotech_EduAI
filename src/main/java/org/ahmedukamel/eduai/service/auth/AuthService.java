package org.ahmedukamel.eduai.service.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.auth.StudentRegistrationRequest;
import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.StudentProfileResponseMapper;
import org.ahmedukamel.eduai.model.AccessToken;
import org.ahmedukamel.eduai.model.Region;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.access_token.AccessTokenService;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final StudentProfileResponseMapper studentProfileResponseMapper;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final StudentRepository studentRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Object registerStudent(Object object) {
        StudentRegistrationRequest request = (StudentRegistrationRequest) object;

        Region region = DatabaseService.get(regionRepository::findById, request.regionId(), Region.class);
        String password = passwordEncoder.encode(request.password());

        User user = User
                .builder()
                .username(request.username().strip())
                .email(request.email().strip().toLowerCase())
                .password(password)
                .gender(request.gender())
                .role(Role.STUDENT)
                .nationality(request.nationality())
                .region(region)
                .enabled(true) // Temporary TODO: Send Activation Email
                .accountNonLocked(true)
                .build();

        User savedUser = userRepository.save(user);

        Student student = Student
                .builder()
                .nid(request.nid())
                .birthDate(request.birthDate())
                .user(savedUser)
                .build();

        Student savedStudent = studentRepository.save(student);

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
        return null;
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