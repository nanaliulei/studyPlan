package com.minibank.controller;

import com.minibank.pojo.Customer;
import com.minibank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liulei
 * @Time: 2021/1/28 16:33
 * @Description
 */

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(path = "/create")
    public int createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return 0;
    }
}
