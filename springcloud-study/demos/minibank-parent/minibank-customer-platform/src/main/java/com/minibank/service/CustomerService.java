package com.minibank.service;

import com.minibank.pojo.Customer;

import java.util.List;

public interface CustomerService {
    //创建客户
    public int createCustomer(Customer customer);
    //客户销户
    public int cancelCustomer(Customer customer);
}
