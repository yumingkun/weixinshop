package com.fish.user.repository;

import com.fish.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mingkunyu on 2019-05-25
 */
public interface UserInfoRepository extends JpaRepository<UserInfo ,String> {

    UserInfo findByOpenid(String openid);
}
