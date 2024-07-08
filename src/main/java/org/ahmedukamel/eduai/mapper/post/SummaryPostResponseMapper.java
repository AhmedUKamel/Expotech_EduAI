package org.ahmedukamel.eduai.mapper.post;

import org.ahmedukamel.eduai.dto.post.SummaryPostResponse;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SummaryPostResponseMapper implements Function<Post, SummaryPostResponse> {

    @Override
    public SummaryPostResponse apply(Post post) {

        boolean viewedByMe = false;
        User user = ContextHolderUtils.getUser();

        if(post.getViewers().stream().map(User::getId).toList().contains(user.getId())){
            viewedByMe = true;
        }

        return new SummaryPostResponse(
                post.getId(),
                post.getCreator().getUsername(),
                post.getCreator().getRole().toString(),
                post.getTextContent(),
                post.getCreatedDate(),
                post.getUpdatedDate(),
                viewedByMe
        );
    }

}
