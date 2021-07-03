package cn.liuliu.dao;

import cn.liuliu.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: liulei
 * @Time: 2021/7/3 17:01
 * @Description
 */

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
