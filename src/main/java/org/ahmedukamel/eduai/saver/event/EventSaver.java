package org.ahmedukamel.eduai.saver.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.EventDetail;
import org.ahmedukamel.eduai.model.enumeration.AttachmentFormat;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EventSaver implements Function<CreateEventRequest, Event> {
    private final EventRepository eventRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Event apply(CreateEventRequest request) {
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);
        String filename = FilenameUtils.getBaseName(request.file().getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(request.file().getOriginalFilename());

        AttachmentFormat fileFormat;
        try {
            fileFormat = AttachmentFormat.valueOf(Objects.requireNonNull(fileExtension).toUpperCase());
        } catch (Exception e) {
            fileFormat = AttachmentFormat.OTHER;
        }

        Event event = Event
                .builder()
                .school(school)
                .eventStartDate(request.startDate())
                .eventEndDate(request.endDate())
                .filename(filename)
                .fileExtension(fileExtension)
                .fileFormat(fileFormat)
                .build();



        EventDetail eventDetail_en = EventDetail
                .builder()
                .event(event)
                .language(Language.ENGLISH)
                .title(request.title_en().strip())
                .description(request.description_en().strip())
                .build(),

                eventDetail_ar = EventDetail
                        .builder()
                        .event(event)
                        .language(Language.ARABIC)
                        .title(request.title_ar().strip())
                        .description(request.description_ar().strip())
                        .build(),

                eventDetail_fr = EventDetail
                        .builder()
                        .event(event)
                        .language(Language.FRENCH)
                        .title(request.title_fr().strip())
                        .description(request.description_fr().strip())
                        .build();

        event.setDetails(Set.of(eventDetail_en, eventDetail_ar, eventDetail_fr));

        return eventRepository.save(event);
    }
}