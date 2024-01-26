package com.like.system.dept.application.service;

import org.springframework.stereotype.Service;

import com.like.system.dept.application.port.in.DeptDeleteUseCase;
import com.like.system.dept.application.port.out.DeptCommandDbPort;

@Service
public class DeptDeleteService implements DeptDeleteUseCase {

	DeptCommandDbPort port;
	
	DeptDeleteService(DeptCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String companyCode, String deptCode) {
		this.port.delete(companyCode, deptCode);
	}

}
