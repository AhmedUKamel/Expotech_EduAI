package org.ahmedukamel.eduai.mapper.profile;

import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Function;

@Component
public class EmployeeProfileResponseMapper extends UserProfileResponseMapper
        implements Function<Employee, EmployeeProfileResponse> {

    public EmployeeProfileResponseMapper(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public EmployeeProfileResponse apply(Employee employee) {
        UserDetail userDetail = super.getDetails(employee);

        Integer positionId = Objects.isNull(employee.getPosition()) ? null : employee.getPosition().getId();

        return new EmployeeProfileResponse(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPicture(),
                StringUtils.hasLength(employee.getPicture()),
                employee.getNid(),
                super.getGender(employee),
                super.getRole(employee),
                super.getNationality(employee),
                super.getReligion(employee),
                employee.getBirthDate(),
                employee.getRegion().getId(),
                employee.getPhoneNumber().toString(),
                userDetail.getName().getFirst(),
                userDetail.getName().getLast(),
                userDetail.getAbout(),
                employee.getPhoneNumber().toString(),
                employee.getSalary(),
                employee.getHireDate(),
                positionId
        );
    }
}