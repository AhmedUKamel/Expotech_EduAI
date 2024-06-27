package org.ahmedukamel.eduai.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEventRequestConverter implements Converter<String, CreateEventRequest> {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public CreateEventRequest convert(@Nonnull String source) {
        return objectMapper.readValue(source, CreateEventRequest.class);
    }
}