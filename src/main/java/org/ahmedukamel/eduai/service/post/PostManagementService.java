package org.ahmedukamel.eduai.service.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.post.CreatePostRequest;
import org.ahmedukamel.eduai.dto.post.DetailedPostResponse;
import org.ahmedukamel.eduai.dto.post.SummaryPostResponse;
import org.ahmedukamel.eduai.dto.post.UpdatePostRequest;
import org.ahmedukamel.eduai.mapper.post.DetailedPostResponseMapper;
import org.ahmedukamel.eduai.mapper.post.SummaryPostResponseMapper;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.PostRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.saver.post.PostSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.post.PostUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class PostManagementService implements IPostManagementService {
    private final PostRepository postRepository;
    private final PostSaver postSaver;
    private final PostUpdater postUpdater;
    private final DetailedPostResponseMapper detailedPostResponseMapper;
    private final SummaryPostResponseMapper summaryPostResponseMapper;
    private final FileSaver fileSaver;

    @Override
    public Object createPost(Object object, MultipartFile file) {
        CreatePostRequest request = (CreatePostRequest) object;
        Post post = postSaver.apply(request, file);

        String message = "Post created Successfully";
        DetailedPostResponse response = detailedPostResponseMapper.apply(post);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updatePost(Long id, Object object) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);
        UpdatePostRequest request = (UpdatePostRequest) object;
        Post updatedPost = postUpdater.apply(post, request);

        String message = "Post updated Successfully";
        DetailedPostResponse response = detailedPostResponseMapper.apply(updatedPost);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePost(Long id) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);
        post.setDeleted(true);
        postRepository.save(post);

        String message = "Post Deleted Successfully";
        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getPost(Long id) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);
        String message = "Post retrieved Successfully";
        DetailedPostResponse response = detailedPostResponseMapper.apply(post);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllPostsForSchool(boolean getActive, int pageSize, int pageNumber) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> posts = postRepository.findAllBySchoolIdAndDeletedOrderByUpdatedDate(school.getId(), !getActive, pageable);
        Page<SummaryPostResponse> postResponses = posts.map(summaryPostResponseMapper);
        String message = "Post retrieved Successfully";
        return new ApiResponse(true, message, postResponses);
    }

    @Override
    public Object uploadPostFile(Long id, MultipartFile file) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);

        try {
            Path oldFilePath = PathConstants.POST_FILES_PATH.resolve(post.getFile());

            Files.delete(oldFilePath);

            String filename = fileSaver.save(file, PathConstants.POST_FILES_PATH);

            Path newFilePath = PathConstants.POST_FILES_PATH.resolve(filename);

            Files.copy(file.getInputStream(), newFilePath);

            post.setFile(filename);

        } catch (IOException exception) {
            throw new RuntimeException("Failed upload file.", exception);
        }

        Post updatedPost = postRepository.save(post);

        String message = "Post file updated Successfully";
        DetailedPostResponse response = detailedPostResponseMapper.apply(updatedPost);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePostFile(Long id) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);
        try {
            Files.delete(PathConstants.POST_FILES_PATH.resolve(post.getFile()));
        } catch (IOException exception) {
            throw new RuntimeException("Failed delete file.", exception);
        }

        post.setFile(null);
        postRepository.save(post);

        String message = "Post file deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public FileResponse getPostFile(Long id) {
        Post post = DatabaseService.get(postRepository::findById, id, Post.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().build());

        try {
            byte[] data = Files.readAllBytes(PathConstants.POST_FILES_PATH.resolve(post.getFile()));

            return new FileResponse(data, headers);
        } catch (IOException exception) {
            throw new RuntimeException("Failed get file.", exception);
        }
    }
}
