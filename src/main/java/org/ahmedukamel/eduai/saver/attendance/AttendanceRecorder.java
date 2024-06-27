package org.ahmedukamel.eduai.saver.attendance;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.attendance.RecordAttendanceRequest;
import org.ahmedukamel.eduai.model.Attendance;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.AttendanceRepository;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AttendanceRecorder implements Function<RecordAttendanceRequest, Attendance> {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    @Override
    public Attendance apply(RecordAttendanceRequest request) {
        DatabaseService.unique(attendanceRepository::existsByStudent_IdAndSection_Id,
                request.studentId(), request.sectionId(), Attendance.class);

        Student student = DatabaseService.get(studentRepository::findById, request.studentId(), Student.class);

        Section section = DatabaseService.get(sectionRepository::findById, request.sectionId(), Section.class);

        Attendance attendance = Attendance
                .builder()
                .student(student)
                .section(section)
                .status(request.status())
                .date(LocalDate.now())
                .build();

        return attendanceRepository.save(attendance);
    }
}