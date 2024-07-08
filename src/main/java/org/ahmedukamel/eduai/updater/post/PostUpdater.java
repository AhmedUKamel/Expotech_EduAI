package org.ahmedukamel.eduai.updater.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.post.UpdatePostRequest;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PostUpdater implements BiFunction<Post, UpdatePostRequest, Post> {

    private final PostRepository postRepository;

    @Override
    public Post apply(Post post, UpdatePostRequest request) {

        post.setTextContent(request.textContent());
        return postRepository.save(post);
    }
}
