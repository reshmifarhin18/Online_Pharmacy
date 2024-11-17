package com.jsp.dao;

import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Address;
import com.jsp.repo.AddressRepo;
import com.jsp.service.AddressService;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}
	
	public Address updateAddress(int addressId,Address address) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			address.setAddressId(addressId);
			address.setMedicalStore(optional.get().getMedicalStore());
			address.setCustomer(optional.get().getCustomer());
			return repo.save(address);
		}
		
		return null;
		
	}
	
	public Address findAddress(int id) {
		Optional<Address> optional=repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public Address deleteAddress(int id) {
		Optional<Address> optional=repo.findById(id);
		if(optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}else {
			return null;
		}
	}

}
