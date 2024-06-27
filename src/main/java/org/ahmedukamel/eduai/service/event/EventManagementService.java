package org.ahmedukamel.eduai.service.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.dto.event.EventResponse;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.mapper.event.EventResponseMapper;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.saver.event.EventSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.event.EventUpdater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventManagementService implements IEventManagementService {
    private final EventResponseMapper eventResponseMapper;
    private final EventRepository eventRepository;
    private final EventUpdater eventUpdater;
    private final EventSaver eventSaver;

    @Override
    public Object createEvent(Object object) {
        CreateEventRequest request = (CreateEventRequest) object;

        Event savedEvent = eventSaver.apply(request);

        String message = "Event created successfully.";

        EventResponse response = eventResponseMapper.apply(savedEvent);

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateEvent(Long id, Object object) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);
        UpdateEventRequest request = (UpdateEventRequest) object;

        Event updatedEvent = eventUpdater.apply(event, request);

        EventResponse response = eventResponseMapper.apply(updatedEvent);

        String message = "Event updated successfully.";

        return new ApiResponse(true, message, response);
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

        EventResponse response = eventResponseMapper.apply(event);
        String message = "Student activity retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllEventsForSchool(Integer schoolId, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Event> events = eventRepository
                .findAllBySchoolIdOrderByEventStartDateDesc(schoolId , pageable);

        Page<EventResponse> response = events
                .map(eventResponseMapper);
        String message = "Events retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}