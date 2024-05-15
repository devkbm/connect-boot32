package com.like.hrm.staff.adapter.in.web.family;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.family.StaffFamilyDeleteUseCase;


@RestController
public class StaffFamilyDeleteController {

	private StaffFamilyDeleteUseCase useCase;
		
	public StaffFamilyDeleteController(StaffFamilyDeleteUseCase service) {
		this.useCase = service;	
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/family/{seq}")
	public ResponseEntity<?> deleteFamily(@RequestParam String companyCode
										 ,@PathVariable String staffId
									     ,@PathVariable Long seq) {
						
		useCase.delete(companyCode, staffId, seq);  									
		
		return toOne(null, MessageUtil.getDeleteMessage(1));									
	}
}
