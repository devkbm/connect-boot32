package com.like.system.systemcode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeSelectUseCase;

@RestController
public class BizCodeSelectController {

	private BizCodeSelectUseCase service;
	
	public BizCodeSelectController(BizCodeSelectUseCase service) {
		this.service = service;
	}
		
	@GetMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> getBizCode(@RequestParam String companyCode
									   ,@PathVariable String typeId
									   ,@PathVariable String code) {
		
		BizCodeSaveDTO dto = service.select(companyCode, typeId, code);
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}			
	
}
