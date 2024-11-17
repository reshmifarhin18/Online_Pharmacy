package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.StaffDto;
import com.jsp.entity.Staff;
import com.jsp.service.StaffService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<StaffDto>>signUp(@RequestParam int AdminId,@RequestParam int storeId,@RequestBody Staff staff){
		return staffService.signupStaff(AdminId, storeId, staff);
	}
	
	public ResponseEntity<ResponseStructure<StaffDto>>updateStaff(@RequestParam int staffId ,@RequestBody Staff staff){
		return staffService.updateStaff(staffId, staff);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<StaffDto>>findStaff(@RequestParam int staffId){
		return staffService.findStaff(staffId);
	}
	
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StaffDto>>deleteStaff(@RequestParam int staffId){
		return staffService.deleteStaff(staffId);
	}
	
	

}
