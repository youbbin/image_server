package com.example.image_server.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private String folder;

    @Builder
    public ImageService(String folder) {
        this.folder = folder;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        // 업로드된 파일 이름에서 날짜 추출 (가정: 파일 이름이 'yyyyMMdd_HHmmss.jpg' 형식)
        String fileName = file.getOriginalFilename();
        String datePart = fileName.split("_")[0];

        // 폴더 생성
//        String folderPath = "/Users/youbin/dev/witlab/image_server/image/ceiling/" + datePart;
        String folderPath = folder + datePart;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 파일을 폴더에 저장
        String filePath = folderPath + "/" + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);

        log.info("Upload completed : "+fileName);
        return file.getOriginalFilename()+" Upload Completed";
    }
}