
package com.like.hrm.hrmcode.application.service.hrmcode;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeSaveUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeCommandDbPort;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;

@Service
public class HrmCodeSaveService implements HrmCodeSaveUseCase {

	HrmCodeCommandDbPort dbPort;
	
	HrmCodeSaveService(HrmCodeCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(HrmCodeSaveDTO dto) {
		HrmCode typeDetailCode = null;
		
		if (hasText(dto.typeId()) && hasText(dto.code())) {
			typeDetailCode = this.dbPort.select(new HrmCodeId(dto.typeId(), dto.code())).orElse(null);
		}
			
		if (typeDetailCode == null) {
			typeDetailCode = dto.newEntity();
		} else {
			typeDetailCode = dto.modify(typeDetailCode);
		}
		
		dbPort.save(typeDetailCode);		
	}
 
}
