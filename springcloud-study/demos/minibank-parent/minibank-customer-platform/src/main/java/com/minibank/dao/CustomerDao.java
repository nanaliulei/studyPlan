package com.minibank.dao;

import com.minibank.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerDao extends JpaRepository<Customer, Long> {
}
