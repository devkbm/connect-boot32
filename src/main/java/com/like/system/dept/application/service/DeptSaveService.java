package com.like.system.dept.application.service;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.dto.DeptSaveDTO;
import com.like.system.dept.application.port.in.DeptSaveUseCase;
import com.like.system.dept.application.port.out.DeptCommandDbPort;
import com.like.system.dept.domain.Dept;

@Service
public class DeptSaveService implements DeptSaveUseCase {
	
	DeptCommandDbPort port;
	
	public DeptSaveService(DeptCommandDbPort port) {
		this.port = port;		
	}
	
	@Override
	public void save(DeptSaveDTO dto) {
		Dept parent = port.select(dto.organizationCode(), dto.parentDeptCode()).orElse(null);
		
		this.port.save(dto.toEntity(parent));
	}

}
