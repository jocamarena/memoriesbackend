package com.example.memoriesbackend.api;

import com.example.memoriesbackend.model.memories.Author;
import com.example.memoriesbackend.model.memories.Memory;
import com.example.memoriesbackend.model.memories.Picture;
import com.example.memoriesbackend.records.MemoryRecord;
import com.example.memoriesbackend.services.AuthorService;
import com.example.memoriesbackend.services.MemoryService;
import com.example.memoriesbackend.services.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/memories")
public class MemoryRestController {
    private final MemoryService memoryService;
    private final AuthorService authorService;
    private final PictureService pictureService;
    public MemoryRestController(MemoryService memoryService, AuthorService authorService, PictureService pictureService) {
        this.pictureService = pictureService;
        this.authorService = authorService;
        this.memoryService = memoryService;
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<MemoryRecord>> getAllMemoriesPaged(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<Memory> memoriesPaged = memoryService.findAllMemoriesPaged(page, size);
        return ResponseEntity.ok(memoriesPaged.map(memory -> new MemoryRecord(memory.getId(), memory.getTitle(), memory.getDescription(), memory.getDate(), memory.getAuthor().getId(), memory.getPicture())));
    }
    @GetMapping
    public ResponseEntity<Iterable<Memory>> getAllMemories() {
        return ResponseEntity.ok(memoryService.findAllMemories(Pageable.unpaged()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Memory> getMemoryById(@PathVariable Long id) {
        Memory memory = memoryService.findMemoryById(id);
        if (memory == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(memory);
        }
    }
    @PostMapping
    public ResponseEntity<Long> saveMemory(@RequestBody MemoryRecord memoryRecord) throws IOException {
        String storageId = pictureService.saveImage(memoryRecord.picture().getImage());
        Picture picture = Picture.builder()
                .title(memoryRecord.picture().getTitle())
                .description(memoryRecord.picture().getDescription())
                .dateTaken(memoryRecord.picture().getDateTaken())
                .image(storageId)
                .build();
        pictureService.savePicture(picture);

        Memory memory = Memory.builder()
                .title(memoryRecord.title())
                .description(memoryRecord.description())
                .date(memoryRecord.date())
                .author(authorService.findById(memoryRecord.authorId()).get())
                .picture(picture)
                .build();
        return ResponseEntity.ok(memoryService.saveMemory(memory));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllMemories() {
        memoryService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
