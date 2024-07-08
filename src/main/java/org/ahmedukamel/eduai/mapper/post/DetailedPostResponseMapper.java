package org.ahmedukamel.eduai.mapper.post;

import org.ahmedukamel.eduai.dto.post.DetailedPostResponse;
import org.ahmedukamel.eduai.dto.post.PostCommentResponse;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.PostComment;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.PostCommentRepository;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class DetailedPostResponseMapper implements Function<Post, DetailedPostResponse> {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentResponseMapper postCommentResponseMapper;

    public DetailedPostResponseMapper(PostCommentRepository postCommentRepository, PostCommentResponseMapper postCommentResponseMapper) {
        this.postCommentRepository = postCommentRepository;
        this.postCommentResponseMapper = postCommentResponseMapper;
    }

    @Override
    public DetailedPostResponse apply(Post post) {

        boolean likedByMe = false;
        User user = ContextHolderUtils.getUser();

        if(post.getLikes().stream().map(User::getId).toList().contains(user.getId())){
            likedByMe = true;
        }


        Pageable pageable = PageRequest.of(0, 3);
        Page<PostComment> originalComments = postCommentRepository
                .findAllByPostIdAndDeletedOrderByUpdatedDate(post.getId(), false, pageable);

        Page<PostCommentResponse> comments = originalComments.map(postCommentResponseMapper);

        return new DetailedPostResponse(
                post.getId(),
                post.getCreator().getUsername(),
                post.getCreator().getRole().toString(),
                post.getTextContent(),
                post.getCreatedDate(),
                post.getUpdatedDate(),
                likedByMe,
                comments
        );
    }

}
