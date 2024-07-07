package org.ahmedukamel.eduai.saver.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.post.CreatePostRequest;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.PostRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PostSaver implements BiFunction<CreatePostRequest, MultipartFile, Post> {

    private final PostRepository postRepository;
    private final FileSaver fileSaver;

    @Override
    public Post apply(CreatePostRequest request, MultipartFile file) {

        final String savedFile;
        try {
            savedFile = fileSaver.save(file, PathConstants.POST_FILES_PATH);
        } catch (IOException exception) {
            throw new RuntimeException("Failed save file.", exception);
        }
        User creator = ContextHolderUtils.getEmployee();
        School school = ContextHolderUtils.getEmployee().getSchool();

        Post post = Post
                .builder()
                .creator(creator)
                .school(school)
                .textContent(request.textContent())
                .file(savedFile)
                .build();
        return postRepository.save(post);
    }
}
