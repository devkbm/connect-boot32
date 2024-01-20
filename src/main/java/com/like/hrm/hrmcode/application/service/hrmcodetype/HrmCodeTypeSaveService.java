package com.like.hrm.hrmcode.application.service.hrmcodetype;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeSaveUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCodeType;

@Service
public class HrmCodeTypeSaveService implements HrmCodeTypeSaveUseCase {

	HrmCodeTypeCommandDbPort dbPort;
	
	HrmCodeTypeSaveService(HrmCodeTypeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(HrmCodeTypeSaveDTO dto) {

		HrmCodeType hrmType = null;
		
		if (StringUtils.hasText(dto.typeId())) hrmType = dbPort.select(dto.typeId()).orElse(null);		
		
		if (hrmType == null) {
			hrmType = dto.newEntity();
		} else {					
			hrmType = dto.modify(hrmType);
		}		
		dbPort.save(hrmType);	
	}
	
}
