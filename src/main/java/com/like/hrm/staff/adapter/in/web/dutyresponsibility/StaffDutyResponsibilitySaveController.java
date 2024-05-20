package com.like.hrm.staff.adapter.in.web.dutyresponsibility;

import jakarta.validation.Valid;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.dutyresponsibility.StaffDutyResponsibilitySaveUseCase;
import com.like.hrm.staff.dto.StaffDutyResponsibilityDTO;

@RestController
public class StaffDutyResponsibilitySaveController {

	private StaffDutyResponsibilitySaveUseCase useCase;
	
	public StaffDutyResponsibilitySaveController(StaffDutyResponsibilitySaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/hrm/staff/{staffId}/dutyresponsibility")
	public ResponseEntity<?> save(@Valid @RequestBody StaffDutyResponsibilityDTO dto) {			
									
		useCase.save(dto);
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}
}
