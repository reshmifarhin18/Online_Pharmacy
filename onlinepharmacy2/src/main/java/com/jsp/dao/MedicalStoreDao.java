package com.jsp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.entity.MedicalStore;
import com.jsp.repo.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {
	@Autowired
	MedicalStoreRepo repo;
	
	
	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		return repo.save(medicalStore);
	}
	
	
	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	
	
	public MedicalStore updateMedicalStore(int storeId,MedicalStore medicalStore) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
		medicalStore.setStoreId(storeId);
		medicalStore.setAdmin(optional.get().getAdmin());
		medicalStore.setAdress(optional.get().getAdress());
		
		return repo.save(medicalStore);
		}else {
			return null;
		}
		
	}
	
	
	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			repo.deleteById(storeId);
			return optional.get();
		}
		else {
			return null;
		}
		
	}
	

}
