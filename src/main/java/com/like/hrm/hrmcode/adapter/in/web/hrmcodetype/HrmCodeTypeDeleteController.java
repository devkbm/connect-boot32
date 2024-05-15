package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeDeleteUseCase;

@RestController
public class HrmCodeTypeDeleteController {

	HrmCodeTypeDeleteUseCase useCase;
	
	HrmCodeTypeDeleteController(HrmCodeTypeDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		useCase.delete(id);						
								 					
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
