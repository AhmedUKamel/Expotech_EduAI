package org.ahmedukamel.eduai.mapper.profile;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EmployeeProfileResponseMapper
        implements Function<Employee, EmployeeProfileResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public EmployeeProfileResponse apply(Employee employee) {
        UserDetail userDetail = UserUtils.getUserDetail(employee);

        Integer positionId = Objects.isNull(employee.getPosition()) ? null : employee.getPosition().getId();

        return new EmployeeProfileResponse(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPicture(),
                StringUtils.hasLength(employee.getPicture()),
                employee.getNid(),
                messageSourceService.getGender(employee.getGender()),
                messageSourceService.getRole(employee.getRole()),
                messageSourceService.getNationality(employee.getNationality()),
                messageSourceService.getReligion(employee.getReligion()),
                employee.getBirthDate(),
                employee.getRegion().getId(),
                employee.getPhoneNumber().toString(),
                userDetail.getName(),
                employee.getAbout(),
                employee.getPhoneNumber().toString(),
                employee.getSalary(),
                employee.getHireDate(),
                positionId
        );
    }
}