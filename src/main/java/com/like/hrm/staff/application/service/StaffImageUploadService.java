package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.like.hrm.staff.application.port.in.StaffImageUploadUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.file.export.FileUploadUseCase;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffImageUploadService implements StaffImageUploadUseCase {
	
	StaffCommandDbPort dbPort;
	FileUploadUseCase uploadUseCase;
	
	StaffImageUploadService(StaffCommandDbPort dbPort
						   ,FileUploadUseCase uploadUseCase) {
		this.dbPort = dbPort;
		this.uploadUseCase = uploadUseCase;
	}
	
	@Override
	public String upload(String companyCode, String staffNo, MultipartFile file) {
		
		Staff entity = this.dbPort.select(companyCode, staffNo)
								  .orElseThrow(() -> new EntityNotFoundException("직원정보가 존재하지 않습니다."));;
		
		if (entity == null) return null;
		
		String path = uploadUseCase.uploadFile(file, "kbm", "SystemUser").fildId().toString();
		
		entity.changeImagePath(path);			
		
		this.dbPort.save(entity);
		
		return path;
	}

}
