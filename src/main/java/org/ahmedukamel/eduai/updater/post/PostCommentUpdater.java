package org.ahmedukamel.eduai.updater.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.post.UpdatePostCommentRequest;
import org.ahmedukamel.eduai.model.PostComment;
import org.ahmedukamel.eduai.repository.PostCommentRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PostCommentUpdater implements BiFunction<PostComment, UpdatePostCommentRequest, PostComment> {

    private final PostCommentRepository postCommentRepository;

    @Override
    public PostComment apply(PostComment postComment, UpdatePostCommentRequest request) {

        postComment.setTextContent(request.textContent());
        return postCommentRepository.save(postComment);
    }
}
