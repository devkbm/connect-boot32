package com.like.hrm.staff.application.service.license;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.license.StaffLicenseSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffLicenseSelectService implements StaffLicenseSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffLicenseSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}	

	@Override
	public StaffLicenseSaveDTO select(String companyCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(companyCode, staffNo)
								 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원정보가 존재하지 않습니다."));
		
		return StaffLicenseSaveDTO.toDTO(staff.getLicenseList().get(staff, seq));
	}

}
