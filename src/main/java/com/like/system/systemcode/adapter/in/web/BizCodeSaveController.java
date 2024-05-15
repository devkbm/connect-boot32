package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeSaveUseCase;

@RestController
public class BizCodeSaveController {

	private BizCodeSaveUseCase service;
	
	public BizCodeSaveController(BizCodeSaveUseCase service) {
		this.service = service;
	}
					
	@PostMapping("/api/system/bizcodetype/bizcode")	
	public ResponseEntity<?> saveBizCode(@RequestBody BizCodeSaveDTO dto) {				
																		
		service.save(dto);						
								 					
		return toList(null, MessageUtil.getSaveMessage(1));
	}			
	
}
