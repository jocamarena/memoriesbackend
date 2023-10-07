package com.example.memoriesbackend.repositories;

import com.example.memoriesbackend.model.memories.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
