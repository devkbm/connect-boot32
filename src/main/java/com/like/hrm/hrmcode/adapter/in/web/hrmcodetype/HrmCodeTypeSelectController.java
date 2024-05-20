package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeSelectUseCase;
import com.like.hrm.hrmcode.dto.HrmCodeTypeSaveDTO;

@RestController
public class HrmCodeTypeSelectController {

	HrmCodeTypeSelectUseCase useCase;
	
	HrmCodeTypeSelectController(HrmCodeTypeSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable String id) {
		
		HrmCodeTypeSaveDTO hrmType = useCase.select(id);
					
		return toOne(hrmType, MessageUtil.getQueryMessage(hrmType == null ? 0 : 1));
	}
}
