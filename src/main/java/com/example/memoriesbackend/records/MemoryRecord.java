package com.example.memoriesbackend.records;

import com.example.memoriesbackend.model.memories.Picture;

import java.time.LocalDate;

public record MemoryRecord(Long id, String title, String description, LocalDate date, Long authorId, Picture picture){
}
