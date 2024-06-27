package org.ahmedukamel.eduai.updater.event;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.EventDetail;
import org.ahmedukamel.eduai.model.enumeration.AttachmentFormat;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.EventRepository;
import org.ahmedukamel.eduai.util.event.EventUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class EventUpdater implements BiFunction<Event, UpdateEventRequest, Event> {
    private final EventRepository eventRepository;

    @Override
    public Event apply(Event event, UpdateEventRequest request) {
        EventDetail eventDetail_en = EventUtils
                .getEventDetail(event, Language.ENGLISH),

                eventDetail_ar = EventUtils
                        .getEventDetail(event, Language.ARABIC),

                eventDetail_fr = EventUtils
                        .getEventDetail(event, Language.FRENCH);

        eventDetail_en.setTitle(request.title_en().strip());
        eventDetail_en.setDescription(request.description_en().strip());

        eventDetail_ar.setTitle(request.title_ar().strip());
        eventDetail_ar.setDescription(request.description_ar().strip());

        eventDetail_fr.setTitle(request.title_fr().strip());
        eventDetail_fr.setDescription(request.description_fr().strip());

        event.setActive(request.active());
        event.setEventStartDate(request.startDate());
        event.setEventEndDate(request.endDate());

        return eventRepository.save(event);
    }
}
