package com.example.memoriesbackend.services;

import com.example.memoriesbackend.model.memories.Picture;
import com.example.memoriesbackend.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PictureService {
    @Value("${image.path}")
    String imagePath;
    private final PictureRepository pictureRepository;
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }
    public List<Picture> findAllPictures() {
        return pictureRepository.findAll();
    }
    public String saveImage(String base64Image) throws IOException {
        byte[] decode = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.UTF_8));
        String imagePath = this.imagePath + UUID.randomUUID() + ".jpg";
        Files.write(Paths.get(imagePath), decode);
        return imagePath;
    }
}
