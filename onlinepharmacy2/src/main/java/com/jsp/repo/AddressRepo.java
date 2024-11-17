package com.jsp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
