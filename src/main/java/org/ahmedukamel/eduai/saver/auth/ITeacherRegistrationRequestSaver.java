package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.ITeacherRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.TeacherDetail;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ITeacherRegistrationRequestSaver
        implements BiFunction<ITeacherRegistrationRequest, School, Teacher> {

    private final UserRegistrationRequestMapper<Teacher> userRegistrationRequestMapper;
    private final PhoneNumberMapper phoneNumberMapper;
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher apply(ITeacherRegistrationRequest request, School school) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        Teacher teacher = userRegistrationRequestMapper.apply(request, Teacher.class);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setSchool(school);

        TeacherDetail teacherDetail_en = TeacherDetail
                .builder()
                .qualification(request.qualification_en())
                .teacher(teacher)
                .language(Language.ENGLISH)
                .build(),

                teacherDetail_ar = TeacherDetail
                        .builder()
                        .qualification(request.qualification_ar())
                        .teacher(teacher)
                        .language(Language.ARABIC)
                        .build(),

                teacherDetail_fr = TeacherDetail
                        .builder()
                        .qualification(request.qualification_fr())
                        .teacher(teacher)
                        .language(Language.FRENCH)
                        .build();

        teacher.setTeacherDetails(Set.of(teacherDetail_en, teacherDetail_ar, teacherDetail_fr));
        return teacherRepository.save(teacher);
    }
}