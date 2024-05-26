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
        UserDetail userDetail = super.getDetails(student.getUser());

        return new StudentProfileResponse(
                student.getUser().getUsername(),
                student.getUser().getEmail(),
                student.getUser().getPicture(),
                StringUtils.hasLength(student.getUser().getPicture()),
                student.getUser().getNid(),
                super.getGender(student.getUser()),
                super.getRole(student.getUser()),
                super.getNationality(student.getUser()),
                super.getReligion(student.getUser()),
                student.getUser().getBirthDate(),
                student.getUser().getRegion().getId(),
                userDetail.getName().getFirst(),
                userDetail.getName().getLast(),
                userDetail.getAbout()
        );
    }
}