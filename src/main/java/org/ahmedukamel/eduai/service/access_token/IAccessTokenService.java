package org.ahmedukamel.eduai.service.access_token;

import org.ahmedukamel.eduai.model.AccessToken;
import org.ahmedukamel.eduai.model.User;

public interface IAccessTokenService {
    AccessToken create(User user);

    AccessToken get(String token);

    boolean exists(String token);

    void delete(String token);
}