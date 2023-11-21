package com.cos.photogramstart.web.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.domain.image.Image;
import com.cos.photogramstart.web.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        System.out.println("이미지경로:"+imageFilePath);

        // I/O 통신 -> 예외가 발생할 수 있다. 예외처리를 꼭 해주자.
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);
    }
}
