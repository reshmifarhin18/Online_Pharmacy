package com.jsp.service;

import javax.security.auth.login.FailedLoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.AdminDao;
import com.jsp.entity.Admin;
import com.jsp.exception.AdminIdNotFoundException;
import com.jsp.exception.FailedResetPasswordException;
import com.jsp.exception.LoginFailedException;
import com.jsp.util.ResponseStructure;
import com.jsp.dto.AdminDto;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<AdminDto>> signUpAdmin(Admin admin){
		
		//save Admin to database
		Admin dbAdmin=adminDao.saveAdmin(admin);
		
		//create Dto for saved the admin
		AdminDto adminDto=new AdminDto();
		adminDto.setAdminId(dbAdmin.getAdminId());
		adminDto.setName(dbAdmin.getName());
		adminDto.setAdminEmail(dbAdmin.getAdminEmail());
		adminDto.setPhoneNumber(dbAdmin.getPhoneNumber());
		
		//Create and populate the response 
		ResponseStructure<AdminDto> structure=new ResponseStructure<>();
		structure.setMessage("Admin signUp Successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<Admin>>updateAdmin(int adminId,Admin admin){
		Admin dbadmin=adminDao.updateAdmin(adminId, admin);
		if(dbadmin!=null) {
			//if id is present
			ResponseStructure<Admin> structure=new ResponseStructure<>();
			structure.setMessage("Admin updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			
			structure.setData(dbadmin);
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
			
		}
		else {
			//id not present
			throw new AdminIdNotFoundException("sorry failed to update admin detailed");
		}
		
	}
	
	  public ResponseEntity<ResponseStructure<Admin>>findAdminById(int adminId)
	    {
	        Admin dbAdmin=adminDao.findAdmin(adminId);
	        if(dbAdmin!=null)
	        {
	            // Id is present

	            ResponseStructure<Admin> structure=new ResponseStructure<>();
	            structure.setMessage("Admin Fatched Successfully");
	            structure.setHttpStatus(HttpStatus.FOUND.value());
	            structure.setData(dbAdmin);
	            return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.FOUND);

	        }else {
	            //id is not present
	            // raise the exception
	            throw new AdminIdNotFoundException("Sorry failed to fetch the admin detail  ");

	        }

	    }
	  
	  public ResponseEntity<ResponseStructure<Admin>> loginAdmin(String email,String password)
	    {
	        Admin dbAdmin=adminDao.findAdminByEmail(email);
	        if(dbAdmin!=null){
	            //admin is present
	            // now check ur password
	            if(password.equals(dbAdmin.getPassword())){
	                // it is vailid email login success

	                ResponseStructure<Admin> structure=new ResponseStructure<>();
	                structure.setMessage("Admin login successfully");
	                structure.setHttpStatus(HttpStatus.OK.value());
	                structure.setData(dbAdmin);

	                return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);

	            }else {
	                // admin password is incorrect
	                // invailid password
	                throw new LoginFailedException("Invailid password");

	            }
	        }else {
	            throw new LoginFailedException("Invailid email");
	        }

	    }
	  
	  public ResponseEntity<ResponseStructure<Admin>>deleteById(int adminId){
		  Admin dbAdmin=adminDao.deleteAdmin(adminId);
				  if(dbAdmin!=null) {
					  //if admin is present
					  ResponseStructure<Admin> structure=new ResponseStructure<>();
					  structure.setMessage("Admin deleted successfully");
					  structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
					  structure.setData(dbAdmin);
					  return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.FORBIDDEN);
                      
				  
				  }
				  else {
					throw new AdminIdNotFoundException("sorry failed to delete the admin details");
				}
		  
	  }
	  
	  public ResponseEntity<ResponseStructure<Admin>>resetPassword(String email,String newpassword,long phone){
		  Admin dbAdmin=adminDao.findAdminByEmail(email);
		  if(dbAdmin!=null) {
			  if(dbAdmin.getPhoneNumber()==phone) {
				  dbAdmin.setPassword(newpassword);
				  Admin updateAdmin=adminDao.updateAdmin(dbAdmin.getAdminId(), dbAdmin);
				  
				  ResponseStructure<Admin> structure=new ResponseStructure<>();
				  structure.setMessage("Admin password reset sccesfully");
				  structure.setHttpStatus(HttpStatus.OK.value());
				  structure.setData(dbAdmin);
				  return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
				  
			  }else {
				throw new FailedResetPasswordException("Invalid Mobile  Number");
			}
			  
		  }else
		  {
			  throw new FailedResetPasswordException("Invailid Email");
		  }
		  
	  }

}
