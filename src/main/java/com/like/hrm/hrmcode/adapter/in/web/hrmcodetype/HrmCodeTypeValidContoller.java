package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeSelectUseCase;

@RestController
public class HrmCodeTypeValidContoller {

	HrmCodeTypeSelectUseCase useCase;
	
	HrmCodeTypeValidContoller(HrmCodeTypeSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/hrmtype/{id}/valid")
	public ResponseEntity<?> validHrmType(@PathVariable(value="id") String id) {
		
		boolean exist = useCase.select(id) != null ? true : false;
					
		return toOne(exist, exist ? "중복된 인사유형 코드가 있습니다." : "사용가능한 코드입니다.");
	}
}
