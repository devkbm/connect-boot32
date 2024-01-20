package com.like.hrm.hrmcode.adapter.in.web.hrmcode;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.service.HrmTypeService;
import com.like.hrm.hrmcode.domain.HrmCodeId;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeSelectController {

	HrmTypeService service;
	
	HrmCodeSelectController(HrmTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable String type, @PathVariable String code) {
		
		HrmCodeSaveDTO dto = service.getTypeDetailCodeDTO(new HrmCodeId(type, code));
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
}
