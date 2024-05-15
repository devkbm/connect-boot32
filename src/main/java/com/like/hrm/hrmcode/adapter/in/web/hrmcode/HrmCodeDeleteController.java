package com.like.hrm.hrmcode.adapter.in.web.hrmcode;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeDeleteUseCase;

@RestController
public class HrmCodeDeleteController {

	HrmCodeDeleteUseCase useCase;			

	public HrmCodeDeleteController(HrmCodeDeleteUseCase useCase) {
		this.useCase = useCase;		
	}																	
	
	@DeleteMapping("/api/hrm/hrmtype/{type}/code/{code}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable String type, @PathVariable String code) {				
																		
		useCase.delete(type, code);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
