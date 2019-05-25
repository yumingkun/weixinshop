package com.fish.user.service;

import com.fish.user.entity.UserInfo;

/**
 * Created by mingkunyu on 2019-05-25
 */
public  interface UserService {
    /**
     * 通过openid来查询用户信息
     * @param openId
     * @return
     */
    UserInfo  findByOpenid(String openId);
}
