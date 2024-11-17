package com.jsp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
