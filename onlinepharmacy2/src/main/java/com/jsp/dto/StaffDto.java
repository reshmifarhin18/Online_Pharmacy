package com.jsp.dto;

import com.jsp.entity.Admin;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class StaffDto {
	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public MedicalStoreDto getMedicalStoreDto() {
		return medicalStoreDto;
	}

	public void setMedicalStoreDto(MedicalStoreDto medicalStoreDto) {
		this.medicalStoreDto = medicalStoreDto;
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int staffId;
	private String staffName;
	private String staffEmail;
	private String staffPassword;
	private long phoneNumber;
	
	@ManyToOne
	private AdminDto adminDto; 
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;

}
