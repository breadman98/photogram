package com.cos.photogramstart.web.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행된다.
    // 따라서 위의 필드 값들만 넣어주면 알아서 생성된다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}