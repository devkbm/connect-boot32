package com.like.system.dept.application.service;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.dto.DeptSaveDTO;
import com.like.system.dept.application.port.in.DeptSelectUseCase;
import com.like.system.dept.application.port.out.DeptCommandDbPort;
import com.like.system.dept.domain.Dept;

@Service
public class DeptSelectService implements DeptSelectUseCase {

	DeptCommandDbPort port;
	
	public DeptSelectService(DeptCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public DeptSaveDTO select(String organizationCode, String deptCode) {
		
		Dept entity = this.port.select(organizationCode, deptCode).orElse(null);
		
		return DeptSaveDTO.toDTO(entity);
	}
	
}
