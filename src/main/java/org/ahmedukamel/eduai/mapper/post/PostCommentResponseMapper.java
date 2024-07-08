package org.ahmedukamel.eduai.mapper.post;

import org.ahmedukamel.eduai.dto.post.DetailedPostResponse;
import org.ahmedukamel.eduai.dto.post.PostCommentResponse;
import org.ahmedukamel.eduai.dto.post.SummaryPostResponse;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.PostComment;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostCommentResponseMapper implements Function<PostComment, PostCommentResponse> {

    @Override
    public PostCommentResponse apply(PostComment postComment) {

        boolean likedByMe = false;
        User user = ContextHolderUtils.getUser();

        if(postComment.getLikes().stream().map(User::getId).toList().contains(user.getId())){
            likedByMe = true;
        }

        return new PostCommentResponse(
                postComment.getId(),
                postComment.getCreator().getUsername(),
                postComment.getCreator().getRole().toString(),
                postComment.getTextContent(),
                postComment.getCreatedDate(),
                postComment.getUpdatedDate(),
                likedByMe
        );
    }

}
