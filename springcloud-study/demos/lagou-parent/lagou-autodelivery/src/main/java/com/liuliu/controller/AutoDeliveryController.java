package com.liuliu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liulei
 * @Time: 2021/1/16 8:49
 * @Description
 */


@RestController
public class AutoDeliveryController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET, path = "/autodelivery/{id}")
    public Integer findOpenState(@PathVariable Long id){
        Integer openState = restTemplate.getForObject("http://localhost:9000/resume/" + id, Integer.class);
        return openState;
    }
}
