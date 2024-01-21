package com.like.hrm.hrmcode.adapter.in.web.hrmcode;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeQueryUseCase;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeQueryController {

	HrmCodeQueryUseCase useCase;
	
	HrmCodeQueryController(HrmCodeQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/hrmtype/code")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmCodeQueryDTO dto) {														
		
		List<HrmCodeSaveDTO> list = useCase.select(dto);																	   
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
