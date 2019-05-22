package com.fish.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingkunyu on 2019-05-19
 */
@RestController
public class ServerController {

    @RequestMapping("/msg")
    public String hello() {
        return "this is product";
    }
}
