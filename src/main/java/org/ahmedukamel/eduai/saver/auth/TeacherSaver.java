package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.TeacherRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.TeacherDetail;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TeacherSaver implements Function<TeacherRegistrationRequest, Teacher> {
    private final PhoneNumberMapper phoneNumberMapper;
    private final TeacherRepository teacherRepository;
    private final UserSaver userSaver;

    @Override
    public Teacher apply(TeacherRegistrationRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        User savedUser = userSaver.apply(request, Role.TEACHER);

        Teacher teacher = Teacher
                .builder()
                .birthDate(request.birthDate())
                .phoneNumber(phoneNumber)
                .user(savedUser)
                .build();

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

        teacher.setDetails(Set.of(teacherDetail_en, teacherDetail_ar, teacherDetail_fr));
        return teacherRepository.save(teacher);
    }
}