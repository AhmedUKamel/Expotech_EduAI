package org.ahmedukamel.eduai.configuration;

import org.ahmedukamel.eduai.constant.LocaleConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleResolverConfiguration {
    @Bean
    LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setSupportedLocales(LocaleConstants.SUPPORTED_LOCALES);
        localeResolver.setDefaultLocale(LocaleConstants.ENGLISH);
        return localeResolver;
    }
}