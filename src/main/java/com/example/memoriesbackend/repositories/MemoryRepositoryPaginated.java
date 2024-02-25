package com.example.memoriesbackend.repositories;

import com.example.memoriesbackend.model.memories.Memory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemoryRepositoryPaginated extends PagingAndSortingRepository<Memory, Long> {

}
