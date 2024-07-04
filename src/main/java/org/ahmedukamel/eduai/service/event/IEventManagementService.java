package org.ahmedukamel.eduai.service.event;

import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IEventManagementService {
    Object createEvent(Object object, MultipartFile file);

    Object updateEvent(Long id, Object object);

    Object addAttendeeToEvent(Long eventId, Long attendeeId);

    Object removeAttendeeFromEvent(Long eventId, Long attendeeId);

    Object deleteEvent(Long id);

    Object getEvent(Long id);

    Object getAllEventsForSchool(boolean getActive, int pageSize, int pageNumber);

    Object uploadEventFile(Long id, MultipartFile pdf);

    Object deleteEventFile(Long id);

    FileResponse getEventFile(Long id);
}