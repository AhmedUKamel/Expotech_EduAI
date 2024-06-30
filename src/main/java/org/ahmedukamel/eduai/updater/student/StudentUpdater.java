package org.ahmedukamel.eduai.updater.student;

import org.ahmedukamel.eduai.dto.student.UpdateStudentRequest;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;

import java.util.function.BiFunction;

public class StudentUpdater implements BiFunction<Student, UpdateStudentRequest,Student> {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    public StudentUpdater(StudentRepository studentRepository, ParentRepository parentRepository) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
    }

    @Override
    public Student apply(Student student, UpdateStudentRequest updateStudentRequest) {

        student.setName(updateStudentRequest.name());
        student.setAge(updateStudentRequest.age());
        student.setGender(updateStudentRequest.gender());
        student.setDateOfBirth(updateStudentRequest.dateOfBirth());
        student.setAddress(updateStudentRequest.address());
        student.setEmail(updateStudentRequest.email());
        student.setFatherName(updateStudentRequest.fatherName());
        student.setMotherName(updateStudentRequest.motherName());
        student.setReligion(updateStudentRequest.religion());
        student.setMobile(updateStudentRequest.mobile());
        student.setParent(DatabaseService.get(parentRepository::findById, updateStudentRequest.parentId(), Parent.class));
        studentRepository.save(student);
        return null;
    }
}
