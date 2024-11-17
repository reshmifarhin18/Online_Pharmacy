package com.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.dto.MedicalStoreDto;
import com.jsp.entity.MedicalStore;
import com.jsp.service.MedicalStoreService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {
	
	@Autowired 
	private MedicalStoreService medicalStoreService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>establishMedicalStore(@RequestParam int adminId,
		@RequestParam	int addressId,@RequestBody MedicalStore medicalStore){
		return medicalStoreService.saveMedicalStore(adminId, addressId, medicalStore);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>findMedicalStore(@RequestParam int  storeId)
	{
		return medicalStoreService.findMedicalStore(storeId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>deleteMedicalStore(@RequestParam int  storeId)
	{
		return medicalStoreService.deleteMedicalStore(storeId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>updateMedicalStore(@RequestParam int  storeId ,@RequestBody MedicalStore medicalStore)
	{
		return medicalStoreService.updateMedicalStore(storeId,medicalStore);
	}

	
}
