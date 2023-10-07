package com.example.memoriesbackend.repositories;

import com.example.memoriesbackend.model.memories.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

}
