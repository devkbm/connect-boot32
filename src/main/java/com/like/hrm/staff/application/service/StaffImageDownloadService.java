package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.StaffImageDownloadUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.system.file.export.FileDownloadUseCase;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class StaffImageDownloadService implements StaffImageDownloadUseCase {

	StaffCommandDbPort dbPort;
	FileDownloadUseCase downloadUseCase;
	
	StaffImageDownloadService(StaffCommandDbPort dbPort
							 ,FileDownloadUseCase downloadUseCase) {
		this.dbPort = dbPort;
		this.downloadUseCase = downloadUseCase;
	}
	
	@Override
	public HttpServletResponse downloadImageFile(String companyCode, String staffNo, HttpServletResponse response) throws Exception {
		Staff entity = this.dbPort.select(companyCode, staffNo)
								  .orElseThrow(() -> new EntityNotFoundException("직원정보가 존재하지 않습니다."));;
				
		downloadUseCase.download(entity.getImagePath(), response);
		
		return response;
	}

}
