package cn.liuliu.service.impl;

import cn.liuliu.service.ICustomerService;
import cn.liuliu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private ICustomerService customerService;

    @Override
    @Transactional
    public void test() {
        System.out.println("test");
    }
}
