package org.ahmedukamel.eduai.service.event;

public interface IEventManagementService {
    Object createEvent(Object object);

    Object updateEvent(Long id, Object object);

    Object deleteEvent(Long id);

    Object getEvent(Long id);

    Object getAllEvents(int pageSize, int pageNumber);
}