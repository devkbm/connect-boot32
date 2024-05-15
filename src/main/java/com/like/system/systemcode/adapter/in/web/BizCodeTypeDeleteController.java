package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.systemcode.application.port.in.BizCodeTypeDeleteUseCase;

@RestController
public class BizCodeTypeDeleteController {

	private BizCodeTypeDeleteUseCase useCase;
	
	public BizCodeTypeDeleteController(BizCodeTypeDeleteUseCase useCase) {
		this.useCase = useCase;
	}	
		
	@DeleteMapping("/api/system/bizcodetype/{typeId}")
	public ResponseEntity<?> deleteBizCodeType(@RequestParam String companyCode
											  ,@PathVariable String typeId) {				
																		
		useCase.delete(companyCode, typeId);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
