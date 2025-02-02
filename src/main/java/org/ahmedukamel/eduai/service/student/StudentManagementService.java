package org.ahmedukamel.eduai.service.student;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.profile.StudentProfileResponse;
import org.ahmedukamel.eduai.dto.student.AddStudentRequest;
import org.ahmedukamel.eduai.mapper.profile.StudentProfileResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.saver.student.IStudentRegistrationRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentManagementService implements IStudentManagementService {
    private final IStudentRegistrationRequestSaver iStudentRegistrationRequestSaver;
    private final StudentProfileResponseMapper studentProfileResponseMapper;
    private final StudentRepository studentRepository;

    @Override
    public Object addStudent(Object object) {
        AddStudentRequest request = (AddStudentRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Student student = iStudentRegistrationRequestSaver.apply(request, school);

        StudentProfileResponse response = studentProfileResponseMapper.apply(student);
        String message = "Student added successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object setStudentAccountLock(Long id, boolean accountLocked) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Student student = DatabaseService.get(studentRepository::findByIdAndSchool_Id,
                id, school.getId(), Student.class);

        student.setAccountNonLocked(!accountLocked);
        studentRepository.save(student);

        String message = "Student account lock set successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getStudent(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        Student student = DatabaseService.get(studentRepository::findByIdAndSchool_Id,
                id, school.getId(), Student.class);

        StudentProfileResponse response = studentProfileResponseMapper.apply(student);
        String message = "Student retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllStudents(int pageSize, int pageNumber, boolean archived) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Student> students = studentRepository.findAllBySchool_IdAndAccountNonLocked(
                school.getId(), !archived, pageable);

        Page<StudentProfileResponse> response = students.map(studentProfileResponseMapper);
        String message = "All students retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}