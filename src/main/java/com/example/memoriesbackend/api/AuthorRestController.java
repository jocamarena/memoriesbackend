package com.example.memoriesbackend.api;

import com.example.memoriesbackend.exceptions.AuthorNotFoundException;
import com.example.memoriesbackend.model.memories.Author;
import com.example.memoriesbackend.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;
    public AuthorRestController(AuthorService authorService){
        this.authorService = authorService;
    }
    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }
    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
        return ResponseEntity.ok(author);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.findById(id).ifPresentOrElse(
                author -> authorService.deleteById(id),
                () -> {
                    throw new AuthorNotFoundException("Author with id " + id + " not found");
                });
        return ResponseEntity.ok().build();
    }
}
