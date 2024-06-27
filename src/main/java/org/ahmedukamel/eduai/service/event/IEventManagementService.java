package org.ahmedukamel.eduai.service.event;

public interface IEventManagementService {
    Object createEvent(Object object);

    Object updateEvent(Long id, Object object);

    Object deleteEvent(Long id);

    Object getEvent(Long id);

    Object getAllEventsForSchool(Integer schoolId, int pageSize, int pageNumber);
}