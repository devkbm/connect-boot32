package com.like.hrm.hrmcode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.service.HrmTypeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeSaveController {

	HrmTypeService service;
	
	HrmCodeSaveController(HrmTypeService service) {
		this.service = service;
	}
	
	@PostMapping("/api/hrm/hrmtype/{type}/code")
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmCodeSaveDTO dto) {				
																			
		service.saveTypeDetailCode(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
