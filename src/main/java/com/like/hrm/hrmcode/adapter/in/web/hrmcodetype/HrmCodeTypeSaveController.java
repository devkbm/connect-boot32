package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeSaveUseCase;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeTypeSaveController {

	HrmCodeTypeSaveUseCase useCase;
	
	HrmCodeTypeSaveController(HrmCodeTypeSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> saveHrmType(@RequestBody HrmCodeTypeSaveDTO dto) {						
																	
		useCase.save(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
	
}
