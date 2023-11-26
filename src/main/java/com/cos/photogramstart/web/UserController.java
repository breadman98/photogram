package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import com.cos.photogramstart.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto",dto);
        return "user/profile";

    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // @AuthenticationPrincipal 어노테이션 사용해서 Authentication 객체에 바로접근
        // System.out.println("세션정보:"+principalDetails.getUser());

        // 직접 찾기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // import core
        PrincipalDetails pDetails = (PrincipalDetails) auth.getPrincipal();
        // System.out.println("직접찾은세션정보"+ pDetails.getUser());

        return "user/update";
    }
}
