package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id){
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 어노테이션 사용
        System.out.println("세션정보:"+principalDetails.getUser());

        // 직접 찾기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // import core
        PrincipalDetails pDetails = (PrincipalDetails) auth.getPrincipal();
        System.out.println("직접찾은세션정보"+ pDetails.getUser());

        return "user/update";
    }
}
