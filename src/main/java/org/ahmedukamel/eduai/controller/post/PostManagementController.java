package org.ahmedukamel.eduai.controller.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.post.CreatePostRequest;
import org.ahmedukamel.eduai.dto.post.UpdatePostRequest;
import org.ahmedukamel.eduai.service.post.PostManagementService;
import org.ahmedukamel.eduai.service.post.IPostManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN')")
@RequestMapping(value = "api/v1/post")
public class PostManagementController {
    private final IPostManagementService service;

    public PostManagementController(PostManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestParam CreatePostRequest request,
                                         @RequestParam MultipartFile file) {
        return ResponseEntity.created(URI.create("/api/v1/post"))
                .body(service.createPost(request, file));
    }

    @PutMapping(value = "{postId}")
    public ResponseEntity<?> updatePost(@Min(value = 1) @PathVariable(value = "postId") Long id,
                                         @Valid @RequestBody UpdatePostRequest request) {
        return ResponseEntity.accepted().body(service.updatePost(id, request));
    }

    @PutMapping(value = "{postId}/upload")
    public ResponseEntity<?> uploadPostFile(@Min(value = 1) @PathVariable(value = "postId") Long id,
                                             @NotEmpty @RequestParam(value = "file") MultipartFile file) {
        return ResponseEntity.accepted().body(service.uploadPostFile(id, file));
    }

    @DeleteMapping(value = "file/{postId}")
    public ResponseEntity<?> deletePostFile(@Min(value = 1) @PathVariable(value = "postId") Long id) {
        return ResponseEntity.accepted().body(service.deletePostFile(id));
    }

    @DeleteMapping(value = "{postId}")
    public ResponseEntity<?> deletePost(@Min(value = 1) @PathVariable(value = "postId") Long id) {
        return ResponseEntity.accepted().body(service.deletePost(id));
    }

    @GetMapping(value = "{postId}")
    public ResponseEntity<?> getPost(@Min(value = 1) @PathVariable(value = "postId") Long id) {
        return ResponseEntity.ok().body(service.getPost(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllPostsForSchool(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                   @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                   @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getAllPostsForSchool(getActive, pageSize, pageNumber));
    }

    @GetMapping(value = "public/{postId}/file")
    public ResponseEntity<?> getPostFile(@Min(value = 1) @PathVariable(value = "postId") Long id) {
        FileResponse response = service.getPostFile(id);
        return ResponseEntity.ok().headers(response.headers()).body(response.date());
    }
}
