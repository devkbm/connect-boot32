package com.like.hrm.staff.adapter.in.web.appointment;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.appointment.StaffAppointmentSelectUseCase;

@RestController
public class StaffAppointmentSelectController {

	private StaffAppointmentSelectUseCase useCase;
	
	public StaffAppointmentSelectController(StaffAppointmentSelectUseCase useCase) {
		this.useCase = useCase;
	}	
	
	@GetMapping("/api/hrm/staff/{staffId}/appointmentrecord/{id}")
	public ResponseEntity<?> getAppointmentRecord(@RequestParam String companyCode
												 ,@PathVariable String staffId
									  			 ,@PathVariable Long id) {						  							
				
		var dto = useCase.select(companyCode, staffId, id);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
	
}
