package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.AdminDto;
import com.jsp.entity.Admin;
import com.jsp.service.AdminService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.util.*;


@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>> signUpAdmin(@RequestBody Admin admin) {
		//TODO: process POST request
		
		return adminService.signUpAdmin(admin);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestParam int id, @RequestBody Admin admin){
		return adminService.updateAdmin(id, admin);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Admin>>findAdminById(@RequestParam int id){
		
		return adminService.findAdminById(id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Admin>>loginAdmin(@RequestParam String email,@RequestParam String password){
		return adminService.loginAdmin(email, password);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Admin>>deleteAdmin(@RequestParam int id){
		return adminService.deleteById(id);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<ResponseStructure<Admin>>resetPassword(@RequestParam String email,@RequestParam String password, @RequestParam long phone){
	   return adminService.resetPassword(email, password, phone);
		
	}

}
