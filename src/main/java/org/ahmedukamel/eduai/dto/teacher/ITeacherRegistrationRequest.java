package org.ahmedukamel.eduai.dto.teacher;

import org.ahmedukamel.eduai.dto.user.IUserRegistrationRequest;
import org.ahmedukamel.eduai.model.enumeration.Qualification;
import org.ahmedukamel.eduai.model.enumeration.Subject;

public interface ITeacherRegistrationRequest extends IUserRegistrationRequest {
    String number();

    Qualification qualification();

    Subject subject();
}