package org.ahmedukamel.eduai.saver.student;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.student.IStudentRegistrationRequest;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class IStudentRegistrationRequestSaver
        implements BiFunction<IStudentRegistrationRequest, School, Student> {

    private final UserRegistrationRequestMapper<Student> userRegistrationRequestMapper;
    private final StudentRepository studentRepository;

    @Override
    public Student apply(IStudentRegistrationRequest request, School school) {
        Student student = userRegistrationRequestMapper.apply(request, Student.class);

        student.setSchool(school);
        student.setRole(Role.STUDENT);

        return studentRepository.save(student);
    }
}