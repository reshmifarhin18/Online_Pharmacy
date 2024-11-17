package com.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.AddressDao;
import com.jsp.dao.AdminDao;
import com.jsp.dao.MedicalStoreDao;
import com.jsp.dto.AddressDto;
import com.jsp.dto.AdminDto;
import com.jsp.dto.MedicalStoreDto;
import com.jsp.entity.Address;
import com.jsp.entity.Admin;
import com.jsp.entity.MedicalStore;
import com.jsp.exception.AddressNotFoundException;
import com.jsp.exception.AdminIdNotFoundException;
import com.jsp.exception.StoreIdNotFoundException;
import com.jsp.util.ResponseStructure;

@Service
public class MedicalStoreService {
	
	@Autowired
	private MedicalStoreDao medicalStoreDao;
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStore medicalStore) {
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
//			admin ios the valid admin
			medicalStore.setAdmin(dbAdmin);
			Address dbAddress=addressDao.findAddress(addressId);
			if(dbAddress!=null) {
//				Address is also valid address...
				medicalStore.setAdress(dbAddress);
				
//				now admin and address both are valid and now i can establish the Medicalstore
				MedicalStore dbMedicalStore=medicalStoreDao.saveMedicalStore(medicalStore);
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
				structure.setMessage("MedicalStore established successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				
				MedicalStoreDto dto=new MedicalStoreDto();
				dto.setStoreId(dbMedicalStore.getStoreId());
				dto.setPhone(dbMedicalStore.getPhone());
				dto.setName(dbMedicalStore.getName());
				dto.setManagerName(dbMedicalStore.getManagerName());
				
				Address address=dbMedicalStore.getAdress();
				AddressDto addressDto=new AddressDto();
				addressDto.setAddressId(address.getAddressId());
				addressDto.setCity(address.getCity());
				addressDto.setPinCode(address.getPinCode());
				addressDto.setState(address.getState());
				
				dto.setAddressDto(addressDto);
				
				Admin admin=dbMedicalStore.getAdmin();
				AdminDto adminDto=new AdminDto();
				adminDto.setAdminEmail(admin.getAdminEmail());
				adminDto.setAdminId(admin.getAdminId());
				adminDto.setName(admin.getName());
				
				dto.setAdminDto(adminDto);
				
				
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
				
				
			}else {
				throw new AddressNotFoundException("Sorry failed to establish the MedicalStore");
			}
		}else{
//			admin is not present it is fake right?? yes
			throw new AdminIdNotFoundException("Sorry Failed to Establish the MedicalStore");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=medicalStoreDao.findMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			store is present and the data updated successfully
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAdress();
			if(address!=null) {
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPinCode(address.getPinCode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setName(admin.getName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
			}else {
				throw new AddressNotFoundException("address not preset here");
			}
			
		}else {
			throw new StoreIdNotFoundException("Sorry failed to findMedicalStore");
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=medicalStoreDao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			store is present and the data updated successfully
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAdress();
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPinCode(address.getPinCode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setName(admin.getName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FORBIDDEN);
			
		}else {
			throw new StoreIdNotFoundException("Sorry failed to delete MedicalStore");
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=medicalStoreDao.updateMedicalStore(storeId,medicalStore);
		if(dbMedicalStore!=null)
		{
//			store is present and the data updated successfully
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAdress();
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPinCode(address.getPinCode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setName(admin.getName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
			
		}else {
			throw new StoreIdNotFoundException("Sorry failed to updateMedicalStore");
		}
	}
}
