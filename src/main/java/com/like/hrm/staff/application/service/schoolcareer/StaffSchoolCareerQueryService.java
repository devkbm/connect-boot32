package com.like.hrm.staff.application.service.schoolcareer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;
import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerQueryUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

import jakarta.persistence.EntityNotFoundException;

@Transactional(readOnly = true)
@Service
public class StaffSchoolCareerQueryService implements StaffSchoolCareerQueryUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSchoolCareerQueryService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public List<StaffSchoolCareerSaveDTO> select(String companyCode, String staffNo) {
		Staff staff = this.dbPort.select(companyCode, staffNo)
								 .orElseThrow(() -> new EntityNotFoundException("직원정보가 존재하지 않습니다."));
		
		return staff.getSchoolCareerList()						  
				    .getStream()
				    .map(e -> StaffSchoolCareerSaveDTO.toDTO(e))
				    .toList();
	}
}
