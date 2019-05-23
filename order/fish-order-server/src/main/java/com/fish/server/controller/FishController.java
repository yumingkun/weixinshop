package com.fish.server.controller;

import com.fish.server.config.FishConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingkunyu on 2019-05-23
 */

@RestController
@RequestMapping("/env")
public class FishController {

    @Autowired
    private FishConfig fishConfig;

    @RequestMapping("/fish")
    public String print(){

        return "name"+fishConfig.getName()+": age"+fishConfig.getAge();
    }
}
