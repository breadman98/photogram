package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.dto.CMResponseDto;
import com.cos.photogramstart.web.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        User user = principalDetails.getUser();
        subscribeService.구독하기(user.getId(),toUserId);
        return new ResponseEntity<>(new CMResponseDto<>(1,"구독하기성공",null), HttpStatus.OK);
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        User user = principalDetails.getUser();
        subscribeService.구독취소하기(user.getId(),toUserId);
        return new ResponseEntity<>(new CMResponseDto<>(1,"구독취소하기성공",null), HttpStatus.OK);
    }
}
