package com.like.hrm.hrmcode.adapter.in.web.hrmcode;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeSaveUseCase;
import com.like.hrm.hrmcode.dto.HrmCodeSaveDTO;

@RestController
public class HrmCodeSaveController {

	HrmCodeSaveUseCase useCase;
	
	HrmCodeSaveController(HrmCodeSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/hrm/hrmtype/{type}/code")
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmCodeSaveDTO dto) {				
																			
		useCase.save(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
