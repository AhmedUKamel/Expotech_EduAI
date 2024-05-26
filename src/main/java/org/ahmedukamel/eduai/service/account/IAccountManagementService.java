package org.ahmedukamel.eduai.service.account;

public interface IAccountManagementService {
    Object getProfile();

    Object updateProfile(Object object);

    Object changePassword(String password, String newPassword);
}