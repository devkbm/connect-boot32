package com.like.hrm.hrmcode.application.service.hrmcodetype;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeQueryUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeTypeQueryDbPort;

@Service
public class HrmCodeTypeQueryService implements HrmCodeTypeQueryUseCase {

	HrmCodeTypeQueryDbPort dbPort;
	
	HrmCodeTypeQueryService(HrmCodeTypeQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<HrmCodeTypeSaveDTO> select(HrmCodeTypeQueryDTO dto) {
		return this.dbPort.select(dto)
						  .stream()
						  .map(e -> HrmCodeTypeSaveDTO.toDTO(e))
						  .toList();
	}

}
