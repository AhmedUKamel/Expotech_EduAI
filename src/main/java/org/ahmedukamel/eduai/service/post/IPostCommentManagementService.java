package org.ahmedukamel.eduai.service.post;

import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IPostCommentManagementService {


    Object createPostComment(Object object, MultipartFile file);

    Object updatePostComment(Long id, Object object);

    Object deletePostComment(Long id);

    Object getPostComment(Long id);

    Object getAllCommentsForPost(Long postId, boolean getActive, int pageSize, int pageNumber);

    Object uploadPostCommentFile(Long id, MultipartFile file);

    Object deletePostCommentFile(Long id);

    FileResponse getPostCommentFile(Long id);
}
