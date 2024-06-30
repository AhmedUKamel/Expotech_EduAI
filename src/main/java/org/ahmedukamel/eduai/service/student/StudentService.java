package org.ahmedukamel.eduai.service.student;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.student.CreateStudentRequest;
import org.ahmedukamel.eduai.dto.student.StudentResponse;
import org.ahmedukamel.eduai.dto.student.UpdateStudentRequest;
import org.ahmedukamel.eduai.mapper.student.StudentResponseMapper;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.saver.student.CreateStudentSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.student.StudentUpdater;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    private final CreateStudentSaver createStudentSaver;
    private final StudentUpdater studentUpdater;
    private final StudentResponseMapper studentResponseMapper;

    public StudentService(StudentRepository studentRepository, CreateStudentSaver createStudentSaver, StudentUpdater studentUpdater, StudentResponseMapper studentResponseMapper) {
        this.studentRepository = studentRepository;
        this.createStudentSaver = createStudentSaver;
        this.studentUpdater = studentUpdater;
        this.studentResponseMapper = studentResponseMapper;
    }

    @Override
    public Object createStudent(Object object) {
        CreateStudentRequest request = (CreateStudentRequest) object;
        Student student = createStudentSaver.apply(request);
        StudentResponse response = studentResponseMapper.apply(student);
        String message = "Student created successfully.";

        return new ApiResponse(true, message, response);

    }

    @Override
    public Object updateStudent(Long id, Object object) {
        Student student = DatabaseService.get(studentRepository::findById, id, Student.class);
        UpdateStudentRequest request = (UpdateStudentRequest) object;
        Student updatedStudent = studentUpdater.apply(student, request);
        StudentResponse response = studentResponseMapper.apply(updatedStudent);
        String message = "Student updated successfully.";
        return new ApiResponse(true, message, response);
    }
    @Override
    public Object deleteStudent(Long id) {
        Student student = DatabaseService.get(studentRepository::findById, id, Student.class);
        studentRepository.delete(student);
        String message = "Student deleted successfully.";
        return  new ApiResponse(true, message, "");
    }

    @Override
    public Object getStudent(Long id) {
        Student student = DatabaseService.get(studentRepository::findById, id, Student.class);
        StudentResponse response = studentResponseMapper.apply(student);
        String message = "Student retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllStudent(long pageSize, long pageNumber) {
        List<Student> students = studentRepository
                                .selectStudentsWithPagination(pageSize, pageSize * (pageNumber - 1));
        List<StudentResponse> response = students
                .stream()
                .map(studentResponseMapper)
                .toList();
        String message = "Students retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
