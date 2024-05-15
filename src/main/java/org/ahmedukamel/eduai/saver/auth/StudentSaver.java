package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.StudentRegistrationRequest;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class StudentSaver implements Function<StudentRegistrationRequest, Student> {
    private final StudentRepository studentRepository;
    private final UserSaver userSaver;

    @Override
    public Student apply(StudentRegistrationRequest request) {
        User savedUser = userSaver.apply(request, Role.STUDENT);

        Student student = Student
                .builder()
                .birthDate(request.birthDate())
                .user(savedUser)
                .build();

        return studentRepository.save(student);
    }
}