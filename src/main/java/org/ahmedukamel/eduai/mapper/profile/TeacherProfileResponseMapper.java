package org.ahmedukamel.eduai.mapper.profile;

import org.ahmedukamel.eduai.dto.profile.TeacherProfileResponse;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.TeacherDetail;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class TeacherProfileResponseMapper extends UserProfileResponseMapper
        implements Function<Teacher, TeacherProfileResponse> {

    public TeacherProfileResponseMapper(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public TeacherProfileResponse apply(Teacher teacher) {
        UserDetail userDetail = super.getDetails(teacher);
        TeacherDetail teacherDetail = this.getDetails(teacher);

        return new TeacherProfileResponse(
                teacher.getId(),
                teacher.getUsername(),
                teacher.getEmail(),
                teacher.getPicture(),
                StringUtils.hasLength(teacher.getPicture()),
                teacher.getNid(),
                super.getGender(teacher),
                super.getRole(teacher),
                super.getNationality(teacher),
                super.getReligion(teacher),
                teacher.getBirthDate(),
                teacher.getRegion().getId(),
                teacher.getPhoneNumber().toString(),
                userDetail.getName().getFirst(),
                userDetail.getName().getLast(),
                userDetail.getAbout(),
                teacher.getPhoneNumber().toString(),
                teacherDetail.getQualification()
        );
    }

    private TeacherDetail getDetails(Teacher teacher) {
        Predicate<TeacherDetail> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return teacher.getTeacherDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}