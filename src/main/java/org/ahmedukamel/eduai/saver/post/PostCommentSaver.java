package org.ahmedukamel.eduai.saver.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.post.CreatePostCommentRequest;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.PostComment;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.PostCommentRepository;
import org.ahmedukamel.eduai.repository.PostRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PostCommentSaver implements BiFunction<CreatePostCommentRequest, MultipartFile, PostComment> {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final FileSaver fileSaver;

    @Override
    public PostComment apply(CreatePostCommentRequest request, MultipartFile file) {

        final String savedFile;
        try {
            savedFile = fileSaver.save(file, PathConstants.POST_COMMENT_FILES_PATH);
        } catch (IOException exception) {
            throw new RuntimeException("Failed save file.", exception);
        }
        User creator = ContextHolderUtils.getEmployee();
        Post post = DatabaseService.get(postRepository::findById, request.postId(), PostComment.class);

        PostComment postComment = PostComment
                .builder()
                .creator(creator)
                .textContent(request.textContent())
                .file(savedFile)
                .post(post)
                .build();
        return postCommentRepository.save(postComment);
    }
}
