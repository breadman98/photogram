package com.cos.photogramstart.web.domain.image;

import com.cos.photogramstart.web.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // 사진 넣을때 같이 넣는 텍스트
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버의 특정 폴더에 저장. DB에 그 저장된 경로를 insert

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    // 이미지 좋아요 정보
    // 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
