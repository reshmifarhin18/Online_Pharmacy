package com.jsp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.MedicalStore;

public interface MedicalStoreRepo extends JpaRepository<MedicalStore, Integer>{

}
