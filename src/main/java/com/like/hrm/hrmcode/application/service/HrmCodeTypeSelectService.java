package com.like.hrm.hrmcode.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.HrmCodeTypeSelectUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeCommandDbPort;

@Service
public class HrmCodeTypeSelectService implements HrmCodeTypeSelectUseCase {

	HrmCodeTypeCommandDbPort dbPort;
	
	HrmCodeTypeSelectService(HrmCodeTypeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
		
	@Override
	public HrmCodeTypeSaveDTO select(String id) {
		return HrmCodeTypeSaveDTO.convert(this.dbPort.select(id).orElse(null));
	}
	
}
