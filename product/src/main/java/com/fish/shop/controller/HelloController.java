package com.fish.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingkunyu on 2019-05-19
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public  String hello(){
        return "hello";
    }
}
