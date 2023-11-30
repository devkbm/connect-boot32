package com.like.system.biztypecode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.biztypecode.application.port.in.BizCodeSelectUseCase;
import com.like.system.biztypecode.application.port.in.dto.BizCodeSaveDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class BizCodeSelectController {

	private BizCodeSelectUseCase service;
	
	public BizCodeSelectController(BizCodeSelectUseCase service) {
		this.service = service;
	}
		
	@GetMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> getBizCode(@RequestParam String organizationCode
									   ,@PathVariable String typeId
									   ,@PathVariable String code) {
		
		BizCodeSaveDTO dto = service.select(organizationCode, typeId, code);
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}			
	
}
