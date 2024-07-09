package org.ahmedukamel.eduai.service.user_management;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class UserManagementService implements IUserManagementService {
    
    private final UserRepository userRepository;
    private final FileSaver fileSaver;

    @Override
    public Object uploadProfilePicture(Long id, MultipartFile picture){
        User user = DatabaseService.get(userRepository::findById, id, Event.class);

        try {
            Path oldFilePath = PathConstants.PROFILE_PHOTOS_PATH.resolve(user.getPicture());

            Files.delete(oldFilePath);

            String filename = fileSaver.save(picture, PathConstants.PROFILE_PHOTOS_PATH);

            Path newFilePath = PathConstants.PROFILE_PHOTOS_PATH.resolve(filename);

            Files.copy(picture.getInputStream(), newFilePath);

            user.setPicture(filename);

        } catch (IOException exception) {
            throw new RuntimeException("Failed upload file.", exception);
        }

        userRepository.save(user);

        String message = "User picture updated successfully.";

        return new ApiResponse(true, message, "");
    }
}