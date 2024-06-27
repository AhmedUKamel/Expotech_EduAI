package org.ahmedukamel.eduai.service.account;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.ParentProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.StudentProfileResponseMapper;
import org.ahmedukamel.eduai.mapper.profile.TeacherProfileResponseMapper;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManagementService implements IAccountManagementService {
    private final EmployeeProfileResponseMapper employeeProfileResponseMapper;
    private final TeacherProfileResponseMapper teacherProfileResponseMapper;
    private final StudentProfileResponseMapper studentProfileResponseMapper;
    private final ParentProfileResponseMapper parentProfileResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Object getProfile() {
        User user = ContextHolderUtils.getUser();

        Object response = switch (user.getRole()) {
            case SUPER_ADMIN, ADMIN -> null;
            case EMPLOYEE -> employeeProfileResponseMapper.apply(user.getEmployee());
            case TEACHER -> teacherProfileResponseMapper.apply(user.getTeacher());
            case STUDENT -> studentProfileResponseMapper.apply(user.getStudent());
            case PARENT -> parentProfileResponseMapper.apply(user.getParent());
        };
        String message = "User profile retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateProfile(Object object) {
        return null;
    }

    @Override
    public Object changePassword(String password, String newPassword) {
        User user = ContextHolderUtils.getUser();

        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            String message = "Password updated successfully.";

            return new ApiResponse(true, message, "");
        }

        throw new RuntimeException("Invalid password");
    }
}