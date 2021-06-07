package com.minibank.api;

import com.minibank.pojo.Customer;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/1/28 18:35
 * @Description
 */

public interface CustomerQuery {

    public Customer findCustomerById(long id);

    public List<Customer> findCustomersByName(String name);
}
