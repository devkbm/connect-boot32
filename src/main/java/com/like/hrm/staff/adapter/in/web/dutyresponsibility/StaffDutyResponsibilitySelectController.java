package com.like.hrm.staff.adapter.in.web.dutyresponsibility;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.dutyresponsibility.StaffDutyResponsibilitySelectUseCase;

@RestController
public class StaffDutyResponsibilitySelectController {

	private StaffDutyResponsibilitySelectUseCase useCase;
	
	public StaffDutyResponsibilitySelectController(StaffDutyResponsibilitySelectUseCase useCase) {
		this.useCase = useCase;		
	}	
	
	@GetMapping("/api/hrm/staff/{staffId}/dutyresponsibility/{seq}")
	public ResponseEntity<?> get(@RequestParam String companyCode
								,@PathVariable String staffId
								,@PathVariable Long seq) {						
				
		var dto = useCase.select(companyCode, staffId, seq);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
