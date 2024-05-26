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
        UserDetail userDetail = super.getDetails(teacher.getUser());
        TeacherDetail teacherDetail = this.getDetails(teacher);

        return new TeacherProfileResponse(
                teacher.getUser().getUsername(),
                teacher.getUser().getEmail(),
                teacher.getUser().getPicture(),
                StringUtils.hasLength(teacher.getUser().getPicture()),
                teacher.getUser().getNid(),
                super.getGender(teacher.getUser()),
                super.getRole(teacher.getUser()),
                super.getNationality(teacher.getUser()),
                super.getReligion(teacher.getUser()),
                teacher.getUser().getBirthDate(),
                teacher.getUser().getRegion().getId(),
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

        return teacher.getDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}