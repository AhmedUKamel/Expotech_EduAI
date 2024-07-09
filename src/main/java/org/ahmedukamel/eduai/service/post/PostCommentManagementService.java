package org.ahmedukamel.eduai.service.post;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.post.CreatePostCommentRequest;
import org.ahmedukamel.eduai.dto.post.PostCommentResponse;
import org.ahmedukamel.eduai.dto.post.UpdatePostCommentRequest;
import org.ahmedukamel.eduai.mapper.post.PostCommentResponseMapper;
import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.PostComment;
import org.ahmedukamel.eduai.repository.PostCommentRepository;
import org.ahmedukamel.eduai.repository.PostRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.saver.post.PostCommentSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.post.PostCommentUpdater;
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
public class PostCommentManagementService implements IPostCommentManagementService {
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;
    private final PostCommentSaver postCommentSaver;
    private final PostCommentUpdater postCommentUpdater;
    private final PostCommentResponseMapper postCommentResponseMapper;
    private final FileSaver fileSaver;

    @Override
    public Object createPostComment(Object object, MultipartFile file) {
        CreatePostCommentRequest request = (CreatePostCommentRequest) object;
        PostComment post = postCommentSaver.apply(request, file);

        String message = "Post Comment created Successfully";
        PostCommentResponse response = postCommentResponseMapper.apply(post);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updatePostComment(Long id, Object object) {
        PostComment postComment = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);
        UpdatePostCommentRequest request = (UpdatePostCommentRequest) object;
        PostComment updatedPostComment = postCommentUpdater.apply(postComment, request);

        String message = "Post Comment updated Successfully";
        PostCommentResponse response = postCommentResponseMapper.apply(updatedPostComment);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePostComment(Long id) {
        PostComment postComment = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);
        postComment.setDeleted(true);
        postCommentRepository.save(postComment);

        String message = "Post Comment Deleted Successfully";
        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getPostComment(Long id) {
        PostComment postComment = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);
        String message = "Post Comment retrieved Successfully";
        PostCommentResponse response = postCommentResponseMapper.apply(postComment);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllCommentsForPost(Long postId, boolean getActive, int pageSize, int pageNumber) {
        Post post = DatabaseService.get(postRepository::findById, postId, Post.class);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PostComment> posts = postCommentRepository.findAllByPostIdAndDeletedOrderByUpdatedDate(post.getId(), !getActive, pageable);
        Page<PostCommentResponse> postResponses = posts.map(postCommentResponseMapper);
        String message = "Post Comments retrieved Successfully";
        return new ApiResponse(true, message, postResponses);
    }

    @Override
    public Object uploadPostCommentFile(Long id, MultipartFile file) {
        PostComment post = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);

        try {
            Path oldFilePath = PathConstants.POST_COMMENT_FILES_PATH.resolve(post.getFile());

            Files.delete(oldFilePath);

            String filename = fileSaver.save(file, PathConstants.POST_COMMENT_FILES_PATH);

            Path newFilePath = PathConstants.POST_COMMENT_FILES_PATH.resolve(filename);

            Files.copy(file.getInputStream(), newFilePath);

            post.setFile(filename);

        } catch (IOException exception) {
            throw new RuntimeException("Failed upload file.", exception);
        }

        PostComment updatedPostComment = postCommentRepository.save(post);

        String message = "Post Comment file updated Successfully";
        PostCommentResponse response = postCommentResponseMapper.apply(updatedPostComment);
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePostCommentFile(Long id) {
        PostComment post = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);
        try {
            Files.delete(PathConstants.POST_COMMENT_FILES_PATH.resolve(post.getFile()));
        } catch (IOException exception) {
            throw new RuntimeException("Failed delete file.", exception);
        }

        post.setFile(null);
        postCommentRepository.save(post);

        String message = "Post Comment file deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public FileResponse getPostCommentFile(Long id) {
        PostComment post = DatabaseService.get(postCommentRepository::findById, id, PostComment.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().build());

        try {
            byte[] data = Files.readAllBytes(PathConstants.POST_COMMENT_FILES_PATH.resolve(post.getFile()));

            return new FileResponse(data, headers);
        } catch (IOException exception) {
            throw new RuntimeException("Failed get file.", exception);
        }
    }
}
