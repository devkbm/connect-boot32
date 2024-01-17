package com.like.hrm.hrmcode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.service.HrmTypeService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeTypeDeleteController {

	HrmTypeService service;
	
	HrmCodeTypeDeleteController(HrmTypeService service) {
		this.service = service;
	}
	
	@DeleteMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteHrmType(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}