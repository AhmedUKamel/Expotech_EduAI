package org.ahmedukamel.eduai.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.news.CreateNewsRequest;
import org.ahmedukamel.eduai.dto.news.UpdateNewsRequest;
import org.ahmedukamel.eduai.service.news.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/news")
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TEACHER')")

public class NewsController {
    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> addNews(@Valid @RequestBody CreateNewsRequest request) {
        return ResponseEntity.created(URI.create("api/v1/news"))
                .body(service.addNews(request));
    }
    @PutMapping(value = "{newsId}")
    public ResponseEntity<?> updateNews(@Min(value = 1) @PathVariable(value = "newsId") Integer id,
                                        @Valid @RequestBody UpdateNewsRequest request) {
        return ResponseEntity.accepted().body(service.updateNews(id, request));
    }
    @DeleteMapping(value = "{newsId}")
    public ResponseEntity<?> deleteNews(@Min(value = 1) @PathVariable(value = "newsId") Integer id) {
        return ResponseEntity.ok().body(service.deleteNews(id));
    }

    @GetMapping(value = "{newsId}")
    public ResponseEntity<?> getNews(@Min(value = 1) @PathVariable(value = "newsId") Integer id) {
        return ResponseEntity.ok().body(service.getNews(id));
    }
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllNews(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                     @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllNews(pageSize, pageNumber));
    }
}
