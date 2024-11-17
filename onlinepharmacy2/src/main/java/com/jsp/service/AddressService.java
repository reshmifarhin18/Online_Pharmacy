package com.jsp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.AddressDao;
import com.jsp.dto.AddressDto;
import com.jsp.entity.Address;
import com.jsp.exception.AddressNotFoundException;
import com.jsp.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	private AddressDto convertToDto(Address address) {
		AddressDto addressDto=new AddressDto();
		addressDto.setAddressId(address.getAddressId());
		addressDto.setCity(address.getCity());
		addressDto.setPinCode(address.getPinCode());
		addressDto.setState(address.getState());
		addressDto.setStreetName(address.getStreetName());
		return addressDto;
		
	}
	

    public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {
        Address dbAddress = addressDao.saveAddress(address);
        AddressDto addressDto = convertToDto(dbAddress);
        
        ResponseStructure<AddressDto> structure = new ResponseStructure<>();
        structure.setMessage("Address data saved successfully");
        structure.setHttpStatus(HttpStatus.CREATED.value());
        structure.setData(addressDto);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }
    
    public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addresssId,Address address) {
    	Address dbAddress=addressDao.updateAddress(addresssId, address);
    	if(dbAddress!=null) {
    	AddressDto addressDto=convertToDto(dbAddress);
    	
    	ResponseStructure<AddressDto> structure=new ResponseStructure<>();
    	structure.setMessage("Addess updated successfully");
    	structure.setHttpStatus(HttpStatus.OK.value());
    	structure.setData(addressDto);
    	
    	return new ResponseEntity<>(structure,HttpStatus.OK);
    	}else {
			throw new AddressNotFoundException("sorry,failed to update Address data");
		}
		
	}
    
    
    public ResponseEntity<ResponseStructure<AddressDto>> findAddress(int addressId) {
    	Address dbAddress=addressDao.findAddress(addressId);
    	if(dbAddress!=null) {
    		AddressDto addressDto=convertToDto(dbAddress);
    	     
    		ResponseStructure<AddressDto> structure=new ResponseStructure<>();
    		structure.setMessage("fetched address detailed successfully");
    		structure.setHttpStatus(HttpStatus.OK.value());
    		structure.setData(addressDto);
    		return new ResponseEntity<>(structure,HttpStatus.OK);
    	}
    	else {
			throw new AddressNotFoundException("sorry, failed to fetch address detailed");
		}
		
	}
    
    public ResponseEntity<ResponseStructure<AddressDto>>deteteAddressById(int addressId){
    	Address dbAddress=addressDao.deleteAddress(addressId);
    	
    	if(dbAddress!=null){
    		AddressDto addressDto=convertToDto(dbAddress);
    		 
    		ResponseStructure<AddressDto> structure=new ResponseStructure<>();
    		structure.setMessage("Address deleted successfully");
    			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
    			structure.setData(addressDto);
    			return new ResponseEntity<>(structure,HttpStatus.FORBIDDEN);
    		
    		
    	}
    	else {
			throw new AddressNotFoundException("sorry, failed to delete address detailed");
		}
    }

}
