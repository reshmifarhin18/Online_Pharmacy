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

import com.jsp.dto.MedicineDto;
import com.jsp.entity.Medicine;
import com.jsp.service.MedicineService;
import com.jsp.util.ResponseStructure;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<MedicineDto>> saveMedicine(@RequestParam int storeId,@RequestBody Medicine medicine){
		return medicineService.saveMedicine(storeId, medicine);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<MedicineDto>> updateMedicine(@RequestParam int medicineId,@RequestBody Medicine medicine){
		return medicineService.updateMedicine(medicineId, medicine);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<MedicineDto>> findMedicineById(@RequestParam int medicineId){
		return medicineService.findMedicineById(medicineId);
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<ResponseStructure<MedicineDto>> findMedicineByName(@RequestParam String medicineName){
		return medicineService.findMedicineByName(medicineName);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<MedicineDto>> deleteMedicineById(@RequestParam int medicineId){
		return medicineService.deleteById(medicineId);
	}


	
}
