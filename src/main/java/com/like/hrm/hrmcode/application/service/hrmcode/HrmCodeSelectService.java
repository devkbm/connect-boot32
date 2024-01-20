package com.like.hrm.hrmcode.application.service.hrmcode;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeSelectUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeId;

@Service
public class HrmCodeSelectService implements HrmCodeSelectUseCase {

	HrmCodeCommandDbPort dbPort;
	
	HrmCodeSelectService(HrmCodeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
		
	@Override
	public HrmCodeSaveDTO select(String type, String code) {	
		return HrmCodeSaveDTO.convert(this.dbPort.select(new HrmCodeId(type, code)).orElse(null));
	}

}
