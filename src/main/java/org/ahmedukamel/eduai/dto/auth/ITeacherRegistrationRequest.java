package org.ahmedukamel.eduai.dto.auth;

public interface ITeacherRegistrationRequest extends IUserRegistrationRequest {
    String number();

    String qualification_en();

    String qualification_ar();

    String qualification_fr();
}