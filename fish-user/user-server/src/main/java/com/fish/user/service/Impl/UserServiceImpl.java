package com.fish.user.service.Impl;

import com.fish.user.entity.UserInfo;
import com.fish.user.repository.UserInfoRepository;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mingkunyu on 2019-05-25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openId) {
        return userInfoRepository.findByOpenid(openId);
    }
}
