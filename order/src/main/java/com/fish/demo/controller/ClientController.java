package com.fish.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Slf4j
@RestController
public class ClientController {

    @Autowired //第二种
    private LoadBalancerClient loadBalancerClient;

    @Autowired //第三种
    private  RestTemplate restTemplate;


    @GetMapping("/getProductMsg")
    public  String getProductMsg(){
        // 第一种方式（写死）
//        RestTemplate restTemplate =new RestTemplate();
//        String msg=restTemplate.getForObject("http://127.0.0.1:8000/msg",String.class);


        //第二种方式
//        RestTemplate restTemplate =new RestTemplate();
//        ServiceInstance serviceInstance=loadBalancerClient.choose("PRODUCT");
//        String url=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
//        String response=restTemplate.getForObject(url,String.class);
//        log.info("response={}",response);


        //第三种方式(利用@LoadBalance 注册restTemplate bean)
        String response=restTemplate.getForObject("http://PRODUCT/msg",String.class);


        return response;

    }
}
