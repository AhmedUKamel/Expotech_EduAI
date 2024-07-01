package org.ahmedukamel.eduai.mapper.profile;

import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
public class StudentProfileResponseMapper extends UserProfileResponseMapper
        implements Function<Student, StudentProfileResponse> {

    public StudentProfileResponseMapper(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public StudentProfileResponse apply(Student student) {
        UserDetail userDetail = super.getDetails(student);

        return new StudentProfileResponse(
                student.getId(),
                student.getUsername(),
                student.getEmail(),
                student.getPicture(),
                StringUtils.hasLength(student.getPicture()),
                student.getNid(),
                super.getGender(student),
                super.getRole(student),
                super.getNationality(student),
                super.getReligion(student),
                student.getBirthDate(),
                student.getRegion().getId(),
                userDetail.getName().getFirst(),
                userDetail.getName().getLast(),
                userDetail.getAbout()
        );
    }
}