package org.ahmedukamel.eduai.mapper.attendance;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.attendance.AttendanceResponse;
import org.ahmedukamel.eduai.model.Attendance;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AttendanceResponseMapper implements Function<Attendance, AttendanceResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public AttendanceResponse apply(Attendance attendance) {
        return new AttendanceResponse(
                attendance.getId(),
                attendance.getStudent().getId(),
                attendance.getSection().getId(),
                messageSourceService.getAttendanceStatus(attendance.getStatus()),
                attendance.getDate()
        );
    }
}