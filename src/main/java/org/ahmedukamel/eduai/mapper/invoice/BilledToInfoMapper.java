package org.ahmedukamel.eduai.mapper.invoice;

import org.ahmedukamel.eduai.dto.invoice.StudentBasicInfo;
import org.ahmedukamel.eduai.model.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BilledToInfoMapper implements Function<Student, StudentBasicInfo> {

    @Override
    public StudentBasicInfo apply(Student student) {
        return new StudentBasicInfo(
                student.getId(),
                student.getUsername(),
                student.getEmail(),
                student.getPicture()
        );
    }
}
