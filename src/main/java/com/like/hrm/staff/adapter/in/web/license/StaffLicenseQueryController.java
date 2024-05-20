package com.like.hrm.staff.adapter.in.web.license;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.hrm.staff.application.port.in.license.StaffLicenseQueryUseCase;
import com.like.hrm.staff.dto.StaffLicenseSaveDTO;

@RestController
public class StaffLicenseQueryController {

	StaffLicenseQueryUseCase useCase;
	
	StaffLicenseQueryController(StaffLicenseQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/staff/{staffId}/license")
	public ResponseEntity<?> getLicense(@RequestParam String companyCode, @PathVariable String staffId) {
						
		List<StaffLicenseSaveDTO> list = useCase.select(companyCode, staffId);
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
