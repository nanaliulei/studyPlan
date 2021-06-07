package com.minibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: liulei
 * @Time: 2021/1/28 19:07
 * @Description
 */

@SpringBootApplication
@EnableDiscoveryClient
public class DepositService {

    public static void main(String[] args) {
        SpringApplication.run(DepositService.class, args);
    }
}
