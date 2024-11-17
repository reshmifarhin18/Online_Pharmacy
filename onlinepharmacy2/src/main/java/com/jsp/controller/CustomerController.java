package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.CustomerDto;
import com.jsp.entity.Customer;
import com.jsp.service.CustomerService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>>signUpCustomer(@RequestParam int addressId,@RequestBody Customer customer){
		  System.out.println("Received addressId: " + addressId);
		    // method logic here
		return customerService.signUpCustomer(addressId, customer);
	}

}
