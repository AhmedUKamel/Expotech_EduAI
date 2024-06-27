package org.ahmedukamel.eduai.service.auth;

public interface IAuthService {
    Object registerStudent(Object object);

    Object registerParent(Object object);

    Object registerTeacher(Object object);

    Object registerEmployee(Object object);

    Object loginUser(String username, String password);

    Object logoutUser();
}