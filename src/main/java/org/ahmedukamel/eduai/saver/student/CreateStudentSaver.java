package org.ahmedukamel.eduai.saver.student;

import org.ahmedukamel.eduai.dto.student.CreateStudentRequest;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class CreateStudentSaver implements Function<CreateStudentRequest, Student> {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    public CreateStudentSaver(StudentRepository studentRepository, ParentRepository parentRepository) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
    }

    @Override
    public Student apply(CreateStudentRequest createStudentRequest) {
        Student student = Student.builder()
                .name(createStudentRequest.name())
                .age(createStudentRequest.age())
                .gender(createStudentRequest.gender())
                .dateOfBirth(createStudentRequest.dateOfBirth())
                .address(createStudentRequest.address())
                .email(createStudentRequest.email())
                .fatherName(createStudentRequest.fatherName())
                .motherName(createStudentRequest.motherName())
                .religion(createStudentRequest.religion())
                .mobile(createStudentRequest.mobile())
                .parent(DatabaseService.get(parentRepository::findById, createStudentRequest.parentId(), Parent.class))
                .build();
        return studentRepository.save(student);
    }
}
