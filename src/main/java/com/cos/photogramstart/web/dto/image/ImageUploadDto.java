package com.cos.photogramstart.web.dto.image;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    // file과 caption을 받을 것이다.
    private MultipartFile file;
    private String caption;
}
