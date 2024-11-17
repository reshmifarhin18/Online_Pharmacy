package com.jsp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.MedicalStoreDao;
import com.jsp.dao.MedicineDao;
import com.jsp.dto.MedicalStoreDto;
import com.jsp.dto.MedicineDto;
import com.jsp.entity.MedicalStore;
import com.jsp.entity.Medicine;
import com.jsp.exception.MedicineIdNotFoundException;
import com.jsp.exception.StoreIdNotFoundException;
import com.jsp.util.ResponseStructure;


@Service
public class MedicineService {
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicalStoreDao medicalStoreDao;
	
	@Autowired(required = true)
    private	ModelMapper modelMapper;
	
	
	public ResponseEntity<ResponseStructure<MedicineDto>> saveMedicine(int storeId,Medicine medicine){
		MedicalStore dbMedicalStore=medicalStoreDao.findMedicalStore(storeId);
		
		if(dbMedicalStore!=null) {
			//if medicalStore is present i can add medicine to  this medical store
			medicine.setMedicalStore(dbMedicalStore);
		    Medicine dbMedicine=medicineDao.saveMedicine(medicine);
		
		MedicineDto medicineDto=this.modelMapper.map(dbMedicine, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbMedicine.getMedicalStore(), MedicalStoreDto.class);
		
		medicineDto.setMedicalStoreDto(medicalStoreDto);
		
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data saved successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(medicineDto);
		
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.CREATED);
		}
		else {
			throw new StoreIdNotFoundException("sorry, failled to save data ");
		}
	}
	
   public ResponseEntity<ResponseStructure<MedicineDto>>updateMedicine(int medicineId,Medicine medicine){
	   Medicine dbMedicine=medicineDao.updateMedicine(medicineId, medicine);
	   if(dbMedicine!=null) {
		   
			MedicineDto medicineDto=this.modelMapper.map(dbMedicine, MedicineDto.class);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbMedicine.getMedicalStore(), MedicalStoreDto.class);
			
			medicineDto.setMedicalStoreDto(medicalStoreDto);
			
			ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
			structure.setMessage("Data upadated succesfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(medicalStoreDto);
			return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.OK);	
		}else {
			throw new MedicineIdNotFoundException("Sorry failed to update medicine");
		}
	   
   }
   
   public ResponseEntity<ResponseStructure<MedicineDto>>findMedicineById(int medicineId){
	   Medicine dbMedicine=medicineDao.findMedicineById(medicineId);
	   if(dbMedicine!=null) {
		   MedicineDto medicineDto=this.modelMapper.map(dbMedicine, MedicineDto.class);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbMedicine.getMedicalStore(), MedicalStoreDto.class);
			
			medicineDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
			structure.setMessage("fetched medicine detaied successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(medicineDto);
			
			return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FOUND);
		   
	   }else {
		  throw new MedicineIdNotFoundException("sorry, failed to fetch medicine detail");
	}
   }
   
   
   
   public ResponseEntity<ResponseStructure<MedicineDto>>findMedicineByName(String medicineName){
	   Medicine dbMedicine=medicineDao.findMedicineByName(medicineName);
	   if(dbMedicine!=null) {
		   MedicineDto medicineDto=this.modelMapper.map(dbMedicine, MedicineDto.class);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbMedicine.getMedicalStore(), MedicalStoreDto.class);
			
			medicineDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
			structure.setMessage("fetched medicine detaied successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(medicineDto);
			
			return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FOUND);
		   
	   }else {
		  throw new MedicineIdNotFoundException("sorry, failed to fetch medicine detail");
	}
   }
	
	
   public ResponseEntity<ResponseStructure<MedicineDto>>deleteById(int medicineId){
	   Medicine dbMedicine=medicineDao.deleteMedicineById(medicineId);
	   if(dbMedicine!=null) {
		   MedicineDto medicineDto=this.modelMapper.map(dbMedicine, MedicineDto.class);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbMedicine.getMedicalStore(), MedicalStoreDto.class);
			
			medicineDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
			structure.setMessage(" medicine  data detaied successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(medicineDto);
			
			return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FORBIDDEN);
		   
	   }else {
		  throw new MedicineIdNotFoundException("sorry, failed to delete medicine detail");
	}
   }
	
	

}
