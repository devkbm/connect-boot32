package com.like.hrm.hrmcode.application.service.hrmcode;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeDeleteUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeId;

@Service
public class HrmCodeDeleteService implements HrmCodeDeleteUseCase {

	HrmCodeCommandDbPort dbPort;

	HrmCodeDeleteService(HrmCodeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String type, String code) {
		this.dbPort.delete(new HrmCodeId(type, code));
	}
}
