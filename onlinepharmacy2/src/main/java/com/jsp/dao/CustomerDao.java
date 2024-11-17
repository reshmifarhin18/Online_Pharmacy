package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Customer;
import com.jsp.repo.CustomerRepo;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepo customerRepo;
	
	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public Customer findCustomer(int customerId) {
		Optional<Customer> optional=customerRepo.findById(customerId);
		if(optional.isPresent()) {
			return  optional.get();
		}else {
			 return null;
		}
	}
	
	
	public Customer updateCustomer(int customerId, Customer dbCustomer) {
		Optional<Customer> optional=customerRepo.findById(customerId);
		if(optional.isPresent()) {
			dbCustomer.setCustomerId(customerId);
			dbCustomer.setBooking(optional.get().getBooking());
			dbCustomer.setAddress(optional.get().getAddress());
			return customerRepo.save(dbCustomer);
		}else {
			return null;
		}
	}

}
