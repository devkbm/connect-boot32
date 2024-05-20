package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.dto.ResponseStaff;

public interface StaffSelectUseCase {
	ResponseStaff select(String companyCode, String staffNo);
}
