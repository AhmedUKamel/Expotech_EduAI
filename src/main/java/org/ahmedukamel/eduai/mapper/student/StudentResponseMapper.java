package org.ahmedukamel.eduai.mapper.student;

import org.ahmedukamel.eduai.dto.student.StudentResponse;
import org.ahmedukamel.eduai.model.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class StudentResponseMapper implements Function<Student, StudentResponse> {
    @Override
    public StudentResponse apply(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getParent().getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                student.getFatherName(),
                student.getMotherName(),
                student.getDateOfBirth(),
                student.getReligion(),
                student.getAddress(),
                student.getEmail(),
                student.getMobile());
    }
}
