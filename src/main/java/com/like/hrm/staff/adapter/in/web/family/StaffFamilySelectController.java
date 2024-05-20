package com.like.hrm.staff.adapter.in.web.family;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.family.StaffFamilySelectUseCase;
import com.like.hrm.staff.dto.StaffFamilySaveDTO;


@RestController
public class StaffFamilySelectController {

	private StaffFamilySelectUseCase useCase;
		
	public StaffFamilySelectController(StaffFamilySelectUseCase useCase) {
		this.useCase = useCase;	
	}	
	
	@GetMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> getFamily(@RequestParam String companyCode
									  ,@PathVariable String staffId
									  ,@PathVariable Long seq) {				
				
		StaffFamilySaveDTO dto =  useCase.select(companyCode, staffId, seq);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));							
	}
}
