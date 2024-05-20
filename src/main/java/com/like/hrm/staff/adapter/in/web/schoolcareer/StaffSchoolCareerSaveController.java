package com.like.hrm.staff.adapter.in.web.schoolcareer;

import jakarta.validation.Valid;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerSaveUseCase;
import com.like.hrm.staff.dto.StaffSchoolCareerSaveDTO;

@RestController
public class StaffSchoolCareerSaveController {

	private StaffSchoolCareerSaveUseCase useCase;
		
	public StaffSchoolCareerSaveController(StaffSchoolCareerSaveUseCase useCase) {
		this.useCase = useCase;
	}
		
	@PostMapping("/api/hrm/staff/{staffId}/schoolcareer")
	public ResponseEntity<?> saveSchoolCareer(@RequestBody @Valid StaffSchoolCareerSaveDTO dto) {
		useCase.save(dto);
											 				
		return toOne(null, MessageUtil.getSaveMessage(1));
	}
	
}
