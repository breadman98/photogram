package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.web.domain.user.User;
import lombok.Data;

@Data // Getter, Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String name;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}