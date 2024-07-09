package org.ahmedukamel.eduai.service.user_management;

import org.springframework.web.multipart.MultipartFile;

public interface IUserManagementService {
    Object uploadProfilePicture(MultipartFile picture);
    Object removeProfilePicture();
}