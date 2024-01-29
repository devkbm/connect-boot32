package com.like.hrm.staff.application.service.family;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.family.StaffFamilyDeleteUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffFamilyDeleteService implements StaffFamilyDeleteUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilyDeleteService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String companyCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(companyCode, staffNo)
								 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원정보가 존재하지 않습니다."));
		
		staff.getFamilyList().remove(staff, seq);
		
		this.dbPort.save(staff);
	}

}
