package com.fish.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingkunyu on 2019-05-23
 */

@Data
@Component
@ConfigurationProperties("fish")
@RefreshScope
public class FishConfig {

    private  String name;
    private  String age;
}
