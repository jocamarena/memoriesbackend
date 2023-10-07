package com.example.memoriesbackend.api;

import com.example.memoriesbackend.model.memories.Memory;
import com.example.memoriesbackend.services.MemoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memories")
public class MemoryRestController {
    private final MemoryService memoryService;
    public MemoryRestController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }
    @GetMapping
    public ResponseEntity<Memory> getMemory() {
        return ResponseEntity.ok().build();
    }
}
