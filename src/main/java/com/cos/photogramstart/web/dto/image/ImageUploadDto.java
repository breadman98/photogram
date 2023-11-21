package com.cos.photogramstart.web.dto.image;


import com.cos.photogramstart.web.domain.image.Image;
import com.cos.photogramstart.web.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    // file과 caption을 받을 것이다.
    private MultipartFile file;
    private String caption;

    // uuid도 붙어있으니까 file.getOriginalFileName() 이거로 인자에 줄 수 없다.
    // 따라서 정확한 값을 위해 imageUrl을 받아와야함.
    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
