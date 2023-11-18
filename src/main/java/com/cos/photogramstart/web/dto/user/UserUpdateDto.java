package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.web.domain.user.User;
import lombok.Data;
import javax.validation.constraints.NotBlank;
@Data
public class UserUpdateDto {

    @NotBlank
    private String name; // required
    @NotBlank
    private String password; // required
    private String website; // optioanl
    private String bio; // optioanl
    private String phone; // optioanl
    private String gender; // optioanl

    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
