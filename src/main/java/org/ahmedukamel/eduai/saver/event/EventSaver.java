package org.ahmedukamel.eduai.saver.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EventSaver implements Function<CreateEventRequest, Event> {
    private final EventRepository eventRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Event apply(CreateEventRequest request) {
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        return null;
    }
}