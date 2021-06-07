package com.minibank.service.impl;

import com.minibank.api.CustomerQuery;
import com.minibank.dao.CustomerDao;
import com.minibank.pojo.Customer;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/1/28 18:48
 * @Description
 */

@DubboService
public class CustomerQueryImpl implements CustomerQuery {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer findCustomerById(long id) {
        Customer customer = customerDao.findById(id).get();
        return customer;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        Example<Customer> example = Example.of(customer);
        List<Customer> customers = customerDao.findAll(example);
        return customers;
    }
}
