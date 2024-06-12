package org.ahmedukamel.eduai.controller.Library;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.Library.BookDTO;
import org.ahmedukamel.eduai.model.Book;
import org.ahmedukamel.eduai.service.Library.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library/books")
public class BookCotroller {

    private final BookService bookService;

//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        return ResponseEntity.ok(bookService.getAllBooks());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> replaceBook (@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.replaceBook(id,bookDTO));
    }
}

