package org.ahmedukamel.eduai.mapper.profile;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class StudentProfileResponseMapper
        implements Function<Student, StudentProfileResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public StudentProfileResponse apply(Student student) {
        UserDetail userDetail = UserUtils.getUserDetail(student);

        return new StudentProfileResponse(
                student.getId(),
                student.getUsername(),
                student.getEmail(),
                student.getPicture(),
                StringUtils.hasLength(student.getPicture()),
                student.getNid(),
                messageSourceService.getGender(student.getGender()),
                messageSourceService.getRole(student.getRole()),
                messageSourceService.getNationality(student.getNationality()),
                messageSourceService.getReligion(student.getReligion()),
                student.getBirthDate(),
                student.getRegion().getId(),
                userDetail.getName(),
                student.getAbout()
        );
    }
}