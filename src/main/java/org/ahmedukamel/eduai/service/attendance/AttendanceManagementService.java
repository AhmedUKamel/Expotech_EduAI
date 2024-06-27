package org.ahmedukamel.eduai.service.attendance;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.attendance.AttendanceResponse;
import org.ahmedukamel.eduai.dto.attendance.RecordAttendanceRequest;
import org.ahmedukamel.eduai.mapper.attendance.AttendanceResponseMapper;
import org.ahmedukamel.eduai.model.Attendance;
import org.ahmedukamel.eduai.repository.AttendanceRepository;
import org.ahmedukamel.eduai.saver.attendance.AttendanceRecorder;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceManagementService implements IAttendanceManagementService {
    private final AttendanceResponseMapper attendanceResponseMapper;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceRecorder attendanceRecorder;

    @Override
    public Object recordAttendance(Object object) {
        RecordAttendanceRequest request = (RecordAttendanceRequest) object;

        Attendance recordedAttendance = attendanceRecorder.apply(request);

        AttendanceResponse response = attendanceResponseMapper.apply(recordedAttendance);
        String message = "Attendance recorded successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAttendance(Long id) {
        Attendance attendance = DatabaseService.get(attendanceRepository::findById, id, Attendance.class);

        AttendanceResponse response = attendanceResponseMapper.apply(attendance);
        String message = "Attendance recorded successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllAttendances(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Attendance> attendances = attendanceRepository.findAll(pageable);

        Page<AttendanceResponse> response = attendances.map(attendanceResponseMapper);
        String message = "Attendance recorded successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAttendancesByStudent(Long id, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Attendance> attendances = attendanceRepository.findAllByStudent_Id(id, pageable);

        Page<AttendanceResponse> response = attendances.map(attendanceResponseMapper);
        String message = "Attendance recorded successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAttendancesBySection(Integer id, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Attendance> attendances = attendanceRepository.findAllBySection_Id(id, pageable);

        Page<AttendanceResponse> response = attendances.map(attendanceResponseMapper);
        String message = "Attendance recorded successfully";

        return new ApiResponse(true, message, response);
    }
}