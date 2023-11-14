package com.example.image_server.controller;

import com.example.image_server.service.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@Configuration
@ConfigurationProperties(prefix = "save")
@Setter
@Getter
public class ImageController {

    private String ceilingPath;
    private String wallPath;

    @PostMapping("ceiling/image/upload")
    public String ceiling_upload(@RequestPart MultipartFile file) throws IOException {
        ImageService ceilingImageService = ImageService.builder()
                .folder(ceilingPath)
                .build();
        return ceilingImageService.uploadFile(file);
    }

    @PostMapping("wall/image/upload")
    public String wall_upload(@RequestPart MultipartFile file) throws IOException {
        ImageService wallImageService = ImageService.builder()
                .folder(wallPath)
                .build();
        return wallImageService.uploadFile(file);
    }
}


