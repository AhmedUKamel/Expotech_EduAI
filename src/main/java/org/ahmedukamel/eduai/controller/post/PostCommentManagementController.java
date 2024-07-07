package org.ahmedukamel.eduai.controller.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.post.CreatePostCommentRequest;
import org.ahmedukamel.eduai.dto.post.UpdatePostCommentRequest;
import org.ahmedukamel.eduai.service.post.IPostCommentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN')")
@RequestMapping(value = "api/v1/postComment")
public class PostCommentManagementController {
    private final IPostCommentManagementService service;

    public PostCommentManagementController(IPostCommentManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPostComment(@Valid @RequestParam CreatePostCommentRequest request,
                                         @RequestParam MultipartFile file) {
        return ResponseEntity.created(URI.create("/api/v1/postComment"))
                .body(service.createPostComment(request, file));
    }

    @PutMapping(value = "{postCommentId}")
    public ResponseEntity<?> updatePostComment(@Min(value = 1) @PathVariable(value = "postCommentId") Long id,
                                         @Valid @RequestBody UpdatePostCommentRequest request) {
        return ResponseEntity.accepted().body(service.updatePostComment(id, request));
    }

    @PutMapping(value = "{postCommentId}/upload")
    public ResponseEntity<?> uploadPostCommentFile(@Min(value = 1) @PathVariable(value = "postCommentId") Long id,
                                             @NotEmpty @RequestParam(value = "file") MultipartFile file) {
        return ResponseEntity.accepted().body(service.uploadPostCommentFile(id, file));
    }

    @DeleteMapping(value = "file/{postCommentId}")
    public ResponseEntity<?> deletePostCommentFile(@Min(value = 1) @PathVariable(value = "postCommentId") Long id) {
        return ResponseEntity.accepted().body(service.deletePostCommentFile(id));
    }

    @DeleteMapping(value = "{postCommentId}")
    public ResponseEntity<?> deletePostComment(@Min(value = 1) @PathVariable(value = "postCommentId") Long id) {
        return ResponseEntity.accepted().body(service.deletePostComment(id));
    }

    @GetMapping(value = "{postCommentId}")
    public ResponseEntity<?> getPostComment(@Min(value = 1) @PathVariable(value = "postCommentId") Long id) {
        return ResponseEntity.ok().body(service.getPostComment(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllCommentsForPost(@Min(value = 1) @RequestParam long postId,
                                                   @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                   @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                   @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getAllCommentsForPost(postId, getActive, pageSize, pageNumber));
    }

    @GetMapping(value = "public/{postCommentId}/file")
    public ResponseEntity<?> getPostCommentFile(@Min(value = 1) @PathVariable(value = "postCommentId") Long id) {
        FileResponse response = service.getPostCommentFile(id);
        return ResponseEntity.ok().headers(response.headers()).body(response.date());
    }
}
