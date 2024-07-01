package org.ahmedukamel.eduai.dto.parent;

import org.ahmedukamel.eduai.dto.user.IUserRegistrationRequest;

public interface IParentRegistrationRequest extends IUserRegistrationRequest {
    String number();

    String occupation_en();

    String occupation_ar();

    String occupation_fr();
}