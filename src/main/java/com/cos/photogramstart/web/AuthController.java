package com.cos.photogramstart.web;

import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.dto.auth.SignupRequestDto;
import com.cos.photogramstart.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupRequestDto signupRequestDto){
        User user = signupRequestDto.toEntity();
        authService.회원가입(user);
        return "auth/signin"; // 회원 가입 성공하면 로그인 페이지로 가기
    }

}