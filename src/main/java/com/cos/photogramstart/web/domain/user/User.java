package com.cos.photogramstart.web.domain.user;

import com.cos.photogramstart.web.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 번호 증가 전략이 db를 따라간다.
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; // 웹사이트 링크
    private String bio; // 자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    // 나는 연관관계의 주인이 아니므로 테이블에 컬럼을 만들지 마시오.
    // 연관관계의 주인은 Image테이블의 User이고 mappedBy는 인스턴스변수인 user로 써준다.
    // user를 select할 때 해당 user id로 등록된 image들을 다 가져오시오
    // Lazy : user를 select할 때 해당 user id로 등록된 image들을 가져오지마시오. 대신 getImages()함수의 image들이 호출될 때 가져오시오
    // Eager : user를 select할 때 해당 user id로 등록된 image들을 전부 join해서 가져오시오
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행된다.
    // 따라서 위의 필드 값들만 넣어주면 알아서 생성된다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}