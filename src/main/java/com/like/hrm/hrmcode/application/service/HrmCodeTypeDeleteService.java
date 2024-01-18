package com.like.hrm.hrmcode.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.in.HrmCodeTypeDeleteUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeCommandDbPort;

@Service
public class HrmCodeTypeDeleteService implements HrmCodeTypeDeleteUseCase {

	HrmCodeTypeCommandDbPort dbPort;
	
	HrmCodeTypeDeleteService(HrmCodeTypeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String id) {
		dbPort.delete(id);
	}

}
