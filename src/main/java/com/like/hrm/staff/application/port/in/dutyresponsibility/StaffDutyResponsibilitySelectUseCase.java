package com.like.hrm.staff.application.port.in.dutyresponsibility;

import com.like.hrm.staff.application.port.dto.StaffDutyResponsibilityDTO;

public interface StaffDutyResponsibilitySelectUseCase {
	
	StaffDutyResponsibilityDTO select(String companyCode, String staffNo, Long seq);
}
