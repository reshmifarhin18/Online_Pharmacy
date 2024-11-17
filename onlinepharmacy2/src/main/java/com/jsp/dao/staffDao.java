package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Staff;
import com.jsp.repo.StafRepo;

@Repository
public class staffDao {
	
	@Autowired
	private StafRepo stafRepo;
	
	public Staff saveStaff(Staff staff) {
		return  stafRepo.save(staff);
		
	}
	public Staff updateStaff(int staffId,Staff staff) {
		Optional<Staff> optional=stafRepo.findById(staffId);
		if(optional.isPresent()) {
			staff.setStaffId(staffId);
			staff.setAdmin(optional.get().getAdmin());
			staff.setMedicalStore(optional.get().getMedicalStore());
			return stafRepo.save(staff);
		}
		
		
		
		return null;
	}
	
	public Staff findStaff(int staffId) {
		
		Optional<Staff> optional=stafRepo.findById(staffId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	
	public Staff deleteStaff(int staffId) {
		Optional<Staff>optional=stafRepo.findById(staffId);
		if(optional.isPresent()) {
			stafRepo.delete(optional.get());

			return optional.get();
			}
		else {
			return null;
		}
					
	}

}
