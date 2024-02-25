package com.example.memoriesbackend.services;

import com.example.memoriesbackend.model.memories.Memory;
import com.example.memoriesbackend.repositories.MemoryRepository;
import com.example.memoriesbackend.repositories.MemoryRepositoryPaginated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final MemoryRepositoryPaginated memoryRepositoryPaginated;
    public MemoryService(MemoryRepository memoryRepository, MemoryRepositoryPaginated memoryRepositoryPaginated) {
        this.memoryRepositoryPaginated = memoryRepositoryPaginated;
        this.memoryRepository = memoryRepository;
    }
    public Page<Memory> findAllMemoriesPaged(int page, int size) {
        return memoryRepositoryPaginated.findAll(Pageable.ofSize(size).withPage(page));
    }
    public Page<Memory> findAllMemories(Pageable pageable){
        return memoryRepositoryPaginated.findAll(pageable);
    }
    public Memory findMemoryById(Long id) {
        return memoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Memory not found"));
    }
    public Long saveMemory(Memory memory) {
        Memory memory1 = memoryRepository.save(memory);
        return memory1.getId();
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
}
