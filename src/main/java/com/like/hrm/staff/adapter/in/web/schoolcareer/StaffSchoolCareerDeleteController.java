package com.like.hrm.staff.adapter.in.web.schoolcareer;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerDeleteUseCase;

@RestController
public class StaffSchoolCareerDeleteController {

	private StaffSchoolCareerDeleteUseCase useCase;
		
	public StaffSchoolCareerDeleteController(StaffSchoolCareerDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/hrm/staff/{staffId}/schoolcareer/{seq}")
	public ResponseEntity<?> deleteSchoolCareer(@RequestParam String companyCode
											   ,@PathVariable String staffId
											   ,@PathVariable Long seq) {
		useCase.delete(companyCode, staffId, seq);
											 				
		return toOne(null, MessageUtil.getDeleteMessage(1));
	}
	
}
