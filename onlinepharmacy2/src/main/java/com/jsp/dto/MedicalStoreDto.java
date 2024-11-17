package com.jsp.dto;

import com.jsp.entity.Address;
import com.jsp.entity.Admin;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class MedicalStoreDto {
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public AdminDto getAdmin() {
		return adminDto;
	}
	public void setAdminDto(AdminDto admin) {
		this.adminDto = admin;
	}
	public AddressDto getAddressDto() {
		return addressDto;
	}
	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}
	private int storeId;
	    private String name;
	    private String managerName;
	    private long phone;
	    
	    @ManyToOne
	    private AdminDto adminDto;
	    @OneToOne
	    private AddressDto addressDto;


}
