package org.ahmedukamel.eduai.mapper.profile;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.MessageSourceConstants;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public abstract class UserProfileResponseMapper {
    private final MessageSource messageSource;

    public final UserDetail getDetails(User user) {
        Predicate<UserDetail> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return user.getDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }

    public String getGender(User user) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_GENDER.formatted(user.getGender()),
                null, LocaleContextHolder.getLocale());
    }

    public String getNationality(User user) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_NATIONALITY.formatted(user.getNationality()),
                null, LocaleContextHolder.getLocale());
    }

    public String getReligion(User user) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_RELIGION.formatted(user.getReligion()),
                null, LocaleContextHolder.getLocale());
    }
}