package com.jsp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.AdminDao;
import com.jsp.dao.MedicalStoreDao;
import com.jsp.dao.staffDao;
import com.jsp.dto.AdminDto;
import com.jsp.dto.MedicalStoreDto;
import com.jsp.dto.StaffDto;
import com.jsp.entity.Admin;
import com.jsp.entity.MedicalStore;
import com.jsp.entity.Staff;
import com.jsp.exception.AdminIdNotFoundException;
import com.jsp.exception.StaffIdNotFoundException;
import com.jsp.exception.StoreIdNotFoundException;
import com.jsp.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private staffDao staffDao;
    @Autowired 
    private AdminDao adminDao;
    @Autowired
    private MedicalStoreDao medicalStoreDao;
    @Autowired
    private ModelMapper modelMapper;

	
    public ResponseEntity<ResponseStructure<StaffDto>> signupStaff(int adminId, int storeId, Staff staff) {
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
			MedicalStore dbMedicalStore=medicalStoreDao.findMedicalStore(storeId);
			
			if(dbMedicalStore!=null) {
//				you can call signup staff method because admin is present and also medicalStore is present
				staff.setAdmin(dbAdmin);
				staff.setMedicalStore(dbMedicalStore);
				Staff dbStaff=staffDao.saveStaff(staff);
				
				StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
				
				AdminDto adminDto=this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class);
				
				staffDto.setAdminDto(adminDto);
				
				MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
				
				staffDto.setMedicalStoreDto(medicalStoreDto);
		
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("staff signedUp successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
			}else {
				
				throw new StoreIdNotFoundException("Sorry failed to signup");
				
			}
		}else {
			throw new AdminIdNotFoundException("Sorry failed to signup staff");
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff=staffDao.updateStaff(staffId,staff);
		if(dbStaff!=null) {
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to update the staff"); 
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=staffDao.findStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to fetch the staff"); 
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=staffDao.deleteStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FORBIDDEN);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to delete the staff"); 
		}
	}
	
	

	
}
