package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.dto.CMResponseDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import com.cos.photogramstart.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMResponseDto<?> update(@PathVariable int id,
                                   @Valid UserUpdateDto userUpdateDto,
                                   BindingResult bindingResult, // @Valid가 적혀있는 파라미터 다음 자리에 꼭 넣어야함
                                   @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError e : bindingResult.getFieldErrors()){
                errorMap.put(e.getField(), e.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패",errorMap);
        }else{
            User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
            principalDetails.setUser(userEntity); // 세션 정보 변경
            return new CMResponseDto<>(1,"회원수정 완료",userEntity);
        }
    }
}
