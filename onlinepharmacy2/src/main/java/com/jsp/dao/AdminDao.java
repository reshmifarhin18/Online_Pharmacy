package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import com.jsp.entity.Admin;
import com.jsp.repo.AdminRepo;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepo adminRepo;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepo.save(admin);
		
	}
	
	public Admin updateAdmin( int adminId,Admin admin) {
		
		Optional<Admin> op=adminRepo.findById(adminId);
		if(op.isPresent())
		{
			admin.setAdminId(adminId);
			return adminRepo.save(admin);
		}
		return null;
		
	}
	
	
	public Admin findAdmin(int adminId) {
		Optional<Admin> op=adminRepo.findById(adminId);
		if(op.isPresent()) {
			Admin admin=op.get();
			return admin;
		}
		return null;
	}
	
	public Admin findAdminByEmail(String email) {
		  Optional<Admin> optional = adminRepo.findByEmail(email);
		  if(optional.isPresent()) {
			  return optional.get();
		  }
		  else {
			return null;
		}
	}
	
	public Admin deleteAdmin(int id) {
		Optional<Admin> optional=adminRepo.findById(id);
		if(optional.isPresent())
		{
			Admin admin= optional.get();
			adminRepo.deleteById(id);
			return admin;
			}
		else  return null;
	}

}
