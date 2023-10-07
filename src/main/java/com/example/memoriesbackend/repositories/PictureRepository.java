package com.example.memoriesbackend.repositories;

import com.example.memoriesbackend.model.memories.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
