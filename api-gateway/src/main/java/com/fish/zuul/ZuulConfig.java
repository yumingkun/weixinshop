package com.fish.zuul;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mingkunyu on 2019-05-25
 */
@Component
public class ZuulConfig {

    /**
     * zuul配置属性的动态注入
     * @return
     */
    @ConfigurationProperties("zuul")
    @RefreshScope //配置中心刷新配置
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

}
