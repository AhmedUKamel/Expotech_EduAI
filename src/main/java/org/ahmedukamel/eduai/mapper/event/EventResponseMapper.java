package org.ahmedukamel.eduai.mapper.event;

import org.ahmedukamel.eduai.dto.event.EventResponse;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.EventDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.event.EventUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EventResponseMapper implements Function<Event, EventResponse> {
    @Override
    public EventResponse apply(Event event) {
        EventDetail eventDetail_en = EventUtils.getEventDetail(
                event, Language.ENGLISH),

                eventDetail_ar = EventUtils.getEventDetail(
                        event, Language.ARABIC),

                eventDetail_fr = EventUtils.getEventDetail(
                        event, Language.FRENCH);

        return EventResponse
                .builder()
                .id(event.getId())
                .creatorId(event.getCreator().getId())
                .startDate(event.getEventStartDate().toString())
                .endDate(event.getEventEndDate().toString())
                .fileName(event.getFilename())
                .fileExtension(event.getFileExtension())
                .fileFormat(event.getFileFormat().toString())
                .title_en(eventDetail_en.getTitle())
                .title_ar(eventDetail_ar.getTitle())
                .title_fr(eventDetail_fr.getTitle())
                .description_en(eventDetail_en.getDescription())
                .description_ar(eventDetail_ar.getDescription())
                .description_fr(eventDetail_fr.getDescription())
                .build();
    }
}
