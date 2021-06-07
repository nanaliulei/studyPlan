package com.minibank.controller;

import com.minibank.api.CustomerQuery;
import com.minibank.pojo.Customer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/1/28 19:09
 * @Description
 */

@RestController
@RequestMapping(path = "/customer")
public class SavingController {

    @DubboReference
    CustomerQuery customerQuery;

    @PostMapping(path = "/find/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name){
        List<Customer> customersByName = customerQuery.findCustomersByName(name);
        return customersByName;
    }

    @PostMapping(path = "/byid/{id}")
    public Customer getCustomerById(@PathVariable long id){
        Customer customerById = customerQuery.findCustomerById(id);
        return customerById;
    }
}
