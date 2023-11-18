package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.dto.CMResponseDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import com.cos.photogramstart.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMResponseDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails){

        User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
        principalDetails.setUser(userEntity); // 세션 정보 변경
        return new CMResponseDto<>(1,"회원수정 완료",userEntity);
    }
}
