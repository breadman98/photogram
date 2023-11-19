package com.cos.photogramstart.web.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying // db에 변경을 주는 쿼리에 넣어야 하는 어노테이션(insert,update,delete)
    @Query(value = "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId, :toUserId,now())",nativeQuery=true)
    void mSubscribe(int fromUserId, int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId)",nativeQuery=true)
    void mUnSubscribe(int fromUserId, int toUserId);
}
