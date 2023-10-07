package com.example.memoriesbackend.services;

import com.example.memoriesbackend.model.memories.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.memoriesbackend.repositories.AuthorRepository;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Long saveAuthor(Author author){
        authorRepository.save(author);
        return author.getId();
    }
    public void deleteAll() {
        authorRepository.deleteAll();
    }
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
    public void deleteAllById(Iterable<Long> ids) {
        authorRepository.deleteAllById(ids);
    }
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
