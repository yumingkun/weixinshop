package com.fish.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mingkunyu on 2019-05-19
 */

@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced ///第三种
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
