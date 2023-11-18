package com.cos.photogramstart.web.service;

import com.cos.photogramstart.web.domain.user.User;
import com.cos.photogramstart.web.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User 회원수정(int id,User user){
        // 1. 영속화
        User userEntity = userRepository.findById(id).get();

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
