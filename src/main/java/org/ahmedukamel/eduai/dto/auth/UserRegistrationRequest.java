package org.ahmedukamel.eduai.dto.auth;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;

import java.time.LocalDate;

public interface UserRegistrationRequest {
    String username();

    String email();

    String password();

    Gender gender();

    Nationality nationality();

    Integer regionId();

    String firstName_en();

    String firstName_ar();

    String firstName_fr();

    String lastName_en();

    String lastName_ar();

    String lastName_fr();

    String about_en();

    String about_ar();

    String about_fr();
}