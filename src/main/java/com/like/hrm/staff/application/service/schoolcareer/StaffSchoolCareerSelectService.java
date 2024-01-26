package com.like.hrm.staff.application.service.schoolcareer;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;
import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffSchoolCareerSelectService implements StaffSchoolCareerSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSchoolCareerSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}	

	@Override
	public StaffSchoolCareerSaveDTO select(String companyCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(companyCode, staffNo);
		
		return StaffSchoolCareerSaveDTO.toDTO(staff.getSchoolCareerList().get(staff, seq));

	}

}
