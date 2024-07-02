package org.ahmedukamel.eduai.dto.user;

import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Nationality;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.time.LocalDate;

public interface IUserRegistrationRequest {
    String username();

    String email();

    String password();

    String nid();

    Gender gender();

    Nationality nationality();

    Integer regionId();

    String name_en();

    String name_ar();

    String name_fr();

    String about();

    LocalDate birthDate();

    Religion religion();
}