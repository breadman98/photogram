package com.cos.photogramstart.web.service;

import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.domain.user.UserRepository;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId,int principalId){

        UserProfileDto dto = new UserProfileDto();


        // SELECT * FROM image WHERE userId = :userId;
        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setImageCount(userEntity.getImages().size());
        dto.setPageOwnerState(pageUserId == principalId); // true:주인 false:주인x


        return dto;
    }

    @Transactional
    public User 회원수정(int id,User user){
        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(()-> new CustomValidationException("찾을 수 없는 user id"+id));

        // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료);
        String rawPasswd = userEntity.getPassword();
        String ecdPasswd = bCryptPasswordEncoder.encode(rawPasswd);
        userEntity.setPassword(ecdPasswd);
        userEntity.setName(user.getName());
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
//        User.builder()
//                .name(user.getName())
//                .bio(user.getBio())
//                .website(user.getWebsite())
//                .phone(user.getPhone())
//                .gender(user.getGender())
//                .build();

        return userEntity;
    } // 더티체킹이 일어나서 업데이트가 완료된다.
}
