package org.ahmedukamel.eduai.service.user_management;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
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
    public Object uploadProfilePicture(MultipartFile picture){
        User user = ContextHolderUtils.getUser();

        try {
            if(user.getPicture() != null) {
                Path oldFilePath = PathConstants.PROFILE_PHOTOS_PATH.resolve(user.getPicture());
                Files.delete(oldFilePath);
            }


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

    @Override
    public Object removeProfilePicture() {
        User user = ContextHolderUtils.getUser();

        if(user.getPicture() != null) {
            try {
                Files.delete(PathConstants.PROFILE_PHOTOS_PATH.resolve(user.getPicture()));
            } catch (IOException exception) {
                throw new RuntimeException("Failed delete file.", exception);
            }
        }


        user.setPicture(null);
        userRepository.save(user);

        String message = "User profile picture deleted successfully.";

        return new ApiResponse(true, message, "");

    }
}