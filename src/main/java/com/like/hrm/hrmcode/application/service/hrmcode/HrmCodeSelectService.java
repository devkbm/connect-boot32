package com.like.hrm.hrmcode.application.service.hrmcode;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeSelectUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeId;
import com.like.hrm.hrmcode.dto.HrmCodeSaveDTO;

@Service
public class HrmCodeSelectService implements HrmCodeSelectUseCase {

	HrmCodeCommandDbPort dbPort;
	
	HrmCodeSelectService(HrmCodeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
		
	@Override
	public HrmCodeSaveDTO select(String type, String code) {	
		return HrmCodeSaveDTO.toDTO(this.dbPort.select(new HrmCodeId(type, code)).orElse(null));
	}

}
