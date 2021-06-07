package com.minibank.service.impl;

import com.minibank.dao.CustomerDao;
import com.minibank.pojo.Customer;
import com.minibank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: liulei
 * @Time: 2021/1/27 16:58
 * @Description
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerDao customerDao;

    @Override
    public int createCustomer(Customer customer) {
        String time = new Date().toString();
        customer.setCreateTime(time);
        customer.setCanceled(false);
        customerDao.save(customer);
        return 0;
    }

    @Override
    public int cancelCustomer(Customer customer) {
        List<Customer> customers = customerDao.findAll(Example.of(customer));
        if (customers.size() == 1){
            Customer canceledCustomer = customers.get(0);
            canceledCustomer.setCanceled(true);
            customerDao.save(canceledCustomer);
            return 0;
        }
        return -1;
    }
}
