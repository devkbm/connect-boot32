package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.systemcode.application.port.in.BizCodeDeleteUseCase;

@RestController
public class BizCodeDeleteController {

	private BizCodeDeleteUseCase service;
	
	public BizCodeDeleteController(BizCodeDeleteUseCase service) {
		this.service = service;
	}		
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}/bizcode/{code}")
	public ResponseEntity<?> deleteBizCode(@RequestParam String companyCode
										  ,@PathVariable String typeId
			 							  ,@PathVariable String code) {				
																		
		service.delete(companyCode, typeId, code);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
