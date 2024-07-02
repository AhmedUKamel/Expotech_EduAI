package org.ahmedukamel.eduai.mapper.profile;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TeacherProfileResponseMapper
        implements Function<Teacher, TeacherProfileResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public TeacherProfileResponse apply(Teacher teacher) {
        UserDetail userDetail = UserUtils.getUserDetail(teacher);

        return new TeacherProfileResponse(
                teacher.getId(),
                teacher.getUsername(),
                teacher.getEmail(),
                teacher.getPicture(),
                StringUtils.hasLength(teacher.getPicture()),
                teacher.getNid(),
                messageSourceService.getGender(teacher.getGender()),
                messageSourceService.getRole(teacher.getRole()),
                messageSourceService.getNationality(teacher.getNationality()),
                messageSourceService.getReligion(teacher.getReligion()),
                teacher.getBirthDate(),
                teacher.getRegion().getId(),
                teacher.getPhoneNumber().toString(),
                userDetail.getName(),
                teacher.getAbout(),
                teacher.getPhoneNumber().toString(),
                messageSourceService.getQualification(teacher.getQualification())
        );
    }
}