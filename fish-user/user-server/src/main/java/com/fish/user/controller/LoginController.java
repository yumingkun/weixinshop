package com.fish.user.controller;

import com.fish.user.constant.CookieConstant;
import com.fish.user.constant.RedisConstant;
import com.fish.user.entity.UserInfo;
import com.fish.user.enums.ResultEnum;
import com.fish.user.enums.RoleEnum;
import com.fish.user.service.UserService;
import com.fish.user.untils.CookieUtil;
import com.fish.user.untils.ResultVOUtils;
import com.fish.user.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by mingkunyu on 2019-05-25
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        //1。openid和数据库匹配

       UserInfo userInfo= userService.findByOpenid(openid);
       if (userInfo==null){
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
       }
        //2。判断角色
        if (RoleEnum.BUYER.getCode()!=userInfo.getRole()){
            return ResultVOUtils.error(ResultEnum.POLE_ERROR);
        }

        //3。cookie里设置openid=adb
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);
        return ResultVOUtils.success();

    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public  ResultVo seller(@RequestParam("openid") String openid,HttpServletResponse response){
        //1。openid和数据库匹配
        UserInfo userInfo= userService.findByOpenid(openid);
        if (userInfo==null){
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
        //2。判断角色
        if (RoleEnum.SELLER.getCode()!=userInfo.getRole()){
            return ResultVOUtils.error(ResultEnum.POLE_ERROR);
        }

        //3:redis设置key=uuid value=xyz
        String token= UUID.randomUUID().toString();
        Integer expire=CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),openid,expire, TimeUnit.SECONDS);

        //4。cookie里设置openid=adb
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.expire);
        return ResultVOUtils.success();


    }


}
