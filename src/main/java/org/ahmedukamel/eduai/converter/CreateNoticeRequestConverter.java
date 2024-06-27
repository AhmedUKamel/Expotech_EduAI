package org.ahmedukamel.eduai.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.ahmedukamel.eduai.dto.notice.CreateNoticeRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateNoticeRequestConverter implements Converter<String, CreateNoticeRequest> {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public CreateNoticeRequest convert(@Nonnull String source) {
        return objectMapper.readValue(source, CreateNoticeRequest.class);
    }
}