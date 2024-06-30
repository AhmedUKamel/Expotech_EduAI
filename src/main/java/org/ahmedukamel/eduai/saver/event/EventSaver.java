package org.ahmedukamel.eduai.saver.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.EventDetail;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class EventSaver implements BiFunction<CreateEventRequest, MultipartFile, Event> {
    private final EventRepository eventRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final FileSaver fileSaver;

    @Override
    public Event apply(CreateEventRequest request, MultipartFile file) {

        final String savedFile;
        try {
            savedFile = fileSaver.save(file, PathConstants.EVENT_FILES_PATH);
        } catch (IOException exception) {
            throw new RuntimeException("Failed save file.", exception);
        }

        User user = DatabaseService.get(userRepository::findById, request.creatorId(), User.class);
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Event event = Event
                .builder()
                .school(school)
                .eventStartTime(request.startTime())
                .eventEndTime(request.endTime())
                .file(savedFile)
                .creator(user)
                .build();

        event.setOrganizers(new HashSet<>());
        for (Long organizerId :
                request.organizersId()) {
            User organizer = DatabaseService.get(userRepository::findById, organizerId, User.class);
            event.getOrganizers().add(organizer);
        }


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