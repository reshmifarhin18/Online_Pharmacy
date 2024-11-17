package com.jsp.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.AddressDao;
import com.jsp.dao.CustomerDao;
import com.jsp.dto.AddressDto;
import com.jsp.dto.CustomerDto;
import com.jsp.entity.Address;
import com.jsp.entity.Customer;
import com.jsp.exception.AddressNotFoundException;
import com.jsp.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<CustomerDto>> signUpCustomer(int addressId,Customer customer){
		Address dbAddress=addressDao.findAddress(addressId);
		if(dbAddress!=null) {
			//if address is presen
			
			List<Address> addresses=new ArrayList<>();
			addresses.add(dbAddress);
			customer.setAddress(addresses);
			Customer dbCustomer=customerDao.saveCustomer(customer);
			
			CustomerDto customerDto=this.modelMapper.map(dbCustomer,CustomerDto.class);
			
			List<Address> list=dbCustomer.getAddress();
			List<AddressDto> addressDtos=new ArrayList<AddressDto>();
			for(Address address:list) {
				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
				addressDtos.add(addressDto);
			}
			
			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("saved customer successfully");
			structure.setHttpStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
			
		}
		else {
			throw new AddressNotFoundException("Sorry failed to save customer");
		}
		
	}
	

}
