package org.ahmedukamel.eduai.service.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.mapper.event.EventResponseMapper;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.saver.event.EventSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventManagementService implements IEventManagementService {
    private final EventResponseMapper eventResponseMapper;
    private final EventRepository eventRepository;
    private final EventSaver eventUpdater;
    private final EventSaver eventSaver;

    @Override
    public Object createEvent(Object object) {
        CreateEventRequest request = (CreateEventRequest) object;

        Event savedEvent = eventSaver.apply(request);

        String message = "Event created successfully.";

        return null;
    }

    @Override
    public Object updateEvent(Long id, Object object) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);
        UpdateEventRequest request = (UpdateEventRequest) object;

        String message = "Student activity updated successfully.";

        return null;
    }

    @Override
    public Object deleteEvent(Long id) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);

        eventRepository.delete(event);

        String message = "Event deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getEvent(Long id) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);


        String message = "Student activity retrieved successfully.";

        return null;
    }

    @Override
    public Object getAllEvents(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);


        return null;
    }
}