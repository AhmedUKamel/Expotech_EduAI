package org.ahmedukamel.eduai.service.public_;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnumerationService implements IEnumerationService {
    private final MessageSource messageSource;

    @Override
    public Object getNationalities() {
        Function<Nationality, String> function = (i) -> messageSource.getMessage(
                "enumeration.nationality.%s".formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(Nationality.values())
                .collect(Collectors.toMap(Nationality::name, function));
        String message = "Successful get all nationalities.";

        return new ApiResponse(true, message, response);
    }
}