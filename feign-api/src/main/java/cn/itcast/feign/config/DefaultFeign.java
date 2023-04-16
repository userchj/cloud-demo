package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeign {
    @Bean
    public Logger.Level LogLevel(){
        return Logger.Level.BASIC;
    }
}
