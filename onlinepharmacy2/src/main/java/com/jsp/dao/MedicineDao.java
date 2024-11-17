package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Medicine;
import com.jsp.repo.MedicineRepo;

@Repository
public class MedicineDao {
	
	@Autowired
	MedicineRepo medicineRepo;
	
	public Medicine saveMedicine(Medicine medicine) {
		return medicineRepo.save(medicine);
	}
	
	public Medicine updateMedicine(int medicineId,Medicine medicine) {
		Optional<Medicine> optional=medicineRepo.findById(medicineId);
		if(optional.isPresent()) {
			medicine.setMedicineId(medicineId);
			medicine.setMedicalStore(optional.get().getMedicalStore());
			return medicineRepo.saveAndFlush(medicine);
			
		}
		else return null;
	}
	
	public Medicine findMedicineById(int medicineId) {
		Optional<Medicine> optional=medicineRepo.findById(medicineId);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	
	
	public Medicine findMedicineByName(String medicineName) {
		Optional<Medicine> optional=medicineRepo.findByName(medicineName);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	public Medicine deleteMedicineById(int medicineId) {
		Optional<Medicine> optional=medicineRepo.findById(medicineId);
		if(optional.isPresent()) {
			medicineRepo.deleteById(medicineId);
			return optional.get() ;
		}
		else {
			return null;
		}
	}
	
	

}
