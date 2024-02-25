package com.example.memoriesbackend.api;

import com.example.memoriesbackend.model.memories.Memory;
import com.example.memoriesbackend.services.MemoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/memories")
public class MemoryRestControllerV2 {
    private final MemoryService memoryService;
    public MemoryRestControllerV2(MemoryService memoryService) {
        this.memoryService = memoryService;
    }
    @GetMapping("/all")
    public Page<Memory> getAllMemories(){
        return memoryService.findAllMemories(Pageable.unpaged());
    }
    @GetMapping
    public Page<Memory> getAllMemoriesPaged(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        return memoryService.findAllMemories(Pageable.ofSize(page == null ? 1 : page).withPage(size == null ? 5 : size));
    }
    @PostMapping
    public Long saveMemory(@RequestBody Memory memory){
        return memoryService.saveMemory(memory);
    }

}
