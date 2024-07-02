package org.ahmedukamel.eduai.saver.teacher;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.teacher.ITeacherRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.springframework.stereotype.Component;

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

        teacher.setQualification(request.qualification());
        teacher.setPhoneNumber(phoneNumber);
        teacher.setRole(Role.TEACHER);
        teacher.setSchool(school);

        return teacherRepository.save(teacher);
    }
}