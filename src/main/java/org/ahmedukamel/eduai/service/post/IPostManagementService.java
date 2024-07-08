package org.ahmedukamel.eduai.service.post;

import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IPostManagementService {

    Object createPost(Object object, MultipartFile file);

    Object updatePost(Long id, Object object);

    Object deletePost(Long id);

    Object getPost(Long id);

    Object getAllPostsForSchool(boolean getActive, int pageSize, int pageNumber);

    Object uploadPostFile(Long id, MultipartFile pdf);

    Object deletePostFile(Long id);

    FileResponse getPostFile(Long id);
}
