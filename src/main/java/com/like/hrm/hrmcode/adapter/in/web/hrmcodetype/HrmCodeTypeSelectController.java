package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.service.HrmTypeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeTypeSelectController {

	HrmTypeService service;
	
	HrmCodeTypeSelectController(HrmTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable String id) {
		
		HrmCodeTypeSaveDTO hrmType = service.getHrmTypeDTO(id);
					
		return toOne(hrmType, MessageUtil.getQueryMessage(hrmType == null ? 0 : 1));
	}
}
