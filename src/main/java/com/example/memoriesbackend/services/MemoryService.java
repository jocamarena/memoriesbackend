package com.example.memoriesbackend.services;

import com.example.memoriesbackend.model.memories.Memory;
import com.example.memoriesbackend.repositories.MemoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemoryService {
    private final MemoryRepository memoryRepository;
    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }
    public void deleteAll() {
        memoryRepository.deleteAll();
    }
    public void deleteById(Long id) {
        memoryRepository.deleteById(id);
    }
    public void deleteAllById(Iterable<Long> ids) {
        memoryRepository.deleteAllById(ids);
    }
    public Long saveMemory(Memory memory) {
        memoryRepository.findById(memory.getId()).ifPresent((memory1) -> {
            throw new RuntimeException("Memory already exists");
        });
        Memory memory1 = memoryRepository.save(memory);
        return memory1.getId();
    }
}
