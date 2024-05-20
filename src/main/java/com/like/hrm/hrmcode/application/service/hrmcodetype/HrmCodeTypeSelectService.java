package com.like.hrm.hrmcode.application.service.hrmcodetype;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeSelectUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeCommandDbPort;
import com.like.hrm.hrmcode.dto.HrmCodeTypeSaveDTO;

@Service
public class HrmCodeTypeSelectService implements HrmCodeTypeSelectUseCase {

	HrmCodeTypeCommandDbPort dbPort;
	
	HrmCodeTypeSelectService(HrmCodeTypeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
		
	@Override
	public HrmCodeTypeSaveDTO select(String id) {
		return HrmCodeTypeSaveDTO.toDTO(this.dbPort.select(id).orElse(null));
	}
	
}
