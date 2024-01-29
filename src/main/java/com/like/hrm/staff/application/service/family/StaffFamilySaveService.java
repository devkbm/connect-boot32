package com.like.hrm.staff.application.service.family;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.family.StaffFamilySaveUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.family.StaffFamily;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffFamilySaveService implements StaffFamilySaveUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilySaveService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(StaffFamilySaveDTO dto) {
		Staff staff = this.dbPort.select(dto.companyCode(), dto.staffNo())
								 .orElseThrow(() -> new EntityNotFoundException(dto.staffNo() + " 직원정보가 존재하지 않습니다."));
		StaffFamily entity = staff.getFamilyList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getFamilyList().add(entity);
		
		this.dbPort.save(staff);		
	}

}
