package com.liuliu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liulei
 * @Time: 2021/1/16 8:46
 * @Description
 */

@SpringBootApplication
public class AutoDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoDeliveryApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
