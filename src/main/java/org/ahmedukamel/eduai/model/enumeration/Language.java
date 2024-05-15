package org.ahmedukamel.eduai.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    ENGLISH("en"),
    ARABIC("ar"),
    FRENCH("fr");

    private final String code;
}