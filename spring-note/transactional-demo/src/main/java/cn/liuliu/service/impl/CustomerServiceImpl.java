package cn.liuliu.service.impl;

import cn.liuliu.dao.CustomerDao;
import cn.liuliu.pojo.Customer;
import cn.liuliu.service.ICustomerService;
import cn.liuliu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/7/3 17:02
 * @Description
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    private ITestService testService;

    @Override
    @Transactional
    public boolean saveCustomers(List<Customer> customerList) {
        for (Customer customer : customerList){
            saveCustomer(customer);
        }
        return false;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try{
            Customer save = customerDao.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
