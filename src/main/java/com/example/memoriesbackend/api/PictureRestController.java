package com.example.memoriesbackend.api;

import com.example.memoriesbackend.model.memories.Picture;
import com.example.memoriesbackend.records.PictureRecord;
import com.example.memoriesbackend.services.PictureService;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PictureRestController {
    private final PictureService pictureService;
    public PictureRestController(PictureService pictureService) {
        this.pictureService = pictureService;
    }
    @GetMapping
    public List<PictureRecord> getAllPictures() {

        return pictureService.findAllPictures().stream().map(picture -> new PictureRecord(picture.getId(), picture.getTitle(), picture.getDescription(), picture.getImage().toString())).toList();
    }
    @PostMapping
    public PictureRecord savePicture(@RequestBody PictureRecord pictureRecord) throws IOException {
        String storageId = pictureService.saveImage(pictureRecord.image());
        Picture picture = Picture.builder()
                .title(pictureRecord.title())
                .description(pictureRecord.description())
                .image(storageId)
                .build();
        return new PictureRecord(pictureService.savePicture(picture).getId(), picture.getTitle(), picture.getDescription(), picture.getImage().toString());
    }
}
