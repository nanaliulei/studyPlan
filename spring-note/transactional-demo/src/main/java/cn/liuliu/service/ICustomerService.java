package cn.liuliu.service;

import cn.liuliu.pojo.Customer;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/7/3 16:55
 * @Description
 */

public interface ICustomerService {

    public boolean saveCustomers(List<Customer> customerList);

    public boolean saveCustomer(Customer customer);

}
