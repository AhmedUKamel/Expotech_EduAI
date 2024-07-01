package org.ahmedukamel.eduai.service.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.dto.event.EventResponse;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.mapper.event.EventResponseMapper;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.saver.event.EventSaver;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.event.EventUpdater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class EventManagementService implements IEventManagementService {
    private final EventResponseMapper eventResponseMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventUpdater eventUpdater;
    private final EventSaver eventSaver;
    private final FileSaver fileSaver;

    @Override
    public Object createEvent(Object object, MultipartFile file) {
        CreateEventRequest request = (CreateEventRequest) object;

        Event savedEvent = eventSaver.apply(request, file);

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
    public Object uploadEventFile(Long id, MultipartFile file) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);

        try {
            Path oldFilePath = PathConstants.EVENT_FILES_PATH.resolve(event.getFile());

            Files.delete(oldFilePath);

            String filename = fileSaver.save(file, PathConstants.EVENT_FILES_PATH);

            Path newFilePath = PathConstants.EVENT_FILES_PATH.resolve(filename);

            Files.copy(file.getInputStream(), newFilePath);

            event.setFile(filename);

        } catch (IOException exception) {
            throw new RuntimeException("Failed upload file.", exception);
        }

        Event updatedEvent = eventRepository.save(event);

        EventResponse response = eventResponseMapper.apply(updatedEvent);
        String message = "Event updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object addAttendeeToEvent(Long eventId, Long attendeeId) {
        Event event = DatabaseService.get(eventRepository::findById, eventId, Event.class);
        User attendee = DatabaseService.get(userRepository::findById, attendeeId, User.class);

        event.getAttendees().add(attendee);

        Event updatedEvent = eventRepository.save(event);

        EventResponse response = eventResponseMapper.apply(updatedEvent);
        String message = "Event updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object removeAttendeeFromEvent(Long eventId, Long attendeeId) {
        Event event = DatabaseService.get(eventRepository::findById, eventId, Event.class);

        eventRepository.deleteAttendeeForEvent(eventId, attendeeId);

        Event eventAfterDelete = DatabaseService.get(eventRepository::findById, eventId, Event.class);

        EventResponse response = eventResponseMapper.apply(eventAfterDelete);
        String message = "Event updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteEventFile(Long id) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);

        try {
            Files.delete(PathConstants.EVENT_FILES_PATH.resolve(event.getFile()));
        } catch (IOException exception) {
            throw new RuntimeException("Failed delete file.", exception);
        }

        event.setFile(null);
        eventRepository.save(event);

        String message = "Event file deleted successfully.";

        return new ApiResponse(true, message, "");
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
        String message = "Event retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllEventsForSchool(Integer schoolId, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Event> events = eventRepository
                .findAllBySchoolIdOrderByEventStartTimeDesc(schoolId , pageable);

        Page<EventResponse> response = events
                .map(eventResponseMapper);
        String message = "Events retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public FileResponse getEventFile(Long id) {
        Event event = DatabaseService.get(eventRepository::findById, id, Event.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().build());

        try {
            byte[] data = Files.readAllBytes(PathConstants.EVENT_FILES_PATH.resolve(event.getFile()));

            return new FileResponse(data, headers);
        } catch (IOException exception) {
            throw new RuntimeException("Failed get file.", exception);
        }
    }
}