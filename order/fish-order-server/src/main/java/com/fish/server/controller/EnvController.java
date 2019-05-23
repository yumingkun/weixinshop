package com.fish.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingkunyu on 2019-05-23
 */
@RestController
@RequestMapping("/env")
@RefreshScope  //刷新bus
public class EnvController {

    @Value("${env}")
    private String env;


    @PostMapping("/print")
    public String print(){

        return  env;
    }
}
