package com.cos.photogramstart.web.service;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.domain.subscribe.Subscribe;
import com.cos.photogramstart.web.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    @Transactional
    public void 구독하기(int fromUserId, int toUserId){
        try{
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch(Exception e){
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId){
        subscribeRepository.mUnSubscribe(fromUserId,toUserId);
    }
}
