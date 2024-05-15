package com.like.hrm.staff.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;
import static com.like.core.web.util.ResponseEntityUtil.toOne;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.dto.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.application.port.dto.StaffQueryConditionDTO;
import com.like.hrm.staff.application.service.StaffQueryService;

@RestController
public class StaffQueryController {
	
	private StaffQueryService service;
	
	public StaffQueryController(StaffQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/hrm/staff")
	public ResponseEntity<?> getStaffList(StaffQueryConditionDTO dto) {
									
		List<ResponseStaff> list = service.getStaff(dto)
												   .stream()
												   .map(e -> ResponseStaff.toDTO(e))
												   .toList(); 
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/record")
	public ResponseEntity<?> getStaffAppointmentRecordList(@RequestParam String companyCode, @PathVariable String id) {
		
		List<?> list = service.getStaffAppointmentRecordList(companyCode, id);								
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/staff/{id}/currentappointment")
	public ResponseEntity<?> getStaffCurrentAppointment(@RequestParam String companyCode, @PathVariable String id) {
		
		ResponseStaffCurrentAppointment dto = service.getStaffCurrentAppointment(companyCode, id);								
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
}
