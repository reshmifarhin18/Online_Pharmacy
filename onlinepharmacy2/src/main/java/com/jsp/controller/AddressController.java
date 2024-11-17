package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.AddressDto;
import com.jsp.entity.Address;
import com.jsp.service.AddressService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AddressDto>>saveAddress(@RequestBody Address address){
		return service.saveAddress(address);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AddressDto>>updateAddress(@RequestParam int id,@RequestBody Address address){
		return service.updateAddress(id, address);
	}
	
	@GetMapping("/findAddress")
	public ResponseEntity<ResponseStructure<AddressDto>>findAddress(@RequestParam int id){
		return service.findAddress(id);
	}
	
	@DeleteMapping("/deleteAddress")
	public ResponseEntity<ResponseStructure<AddressDto>>deleteAddress(@RequestParam int id){
		return service.deteteAddressById(id);
	}
	

}
