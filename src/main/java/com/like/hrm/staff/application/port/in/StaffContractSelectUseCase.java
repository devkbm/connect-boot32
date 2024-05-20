package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.dto.StaffContactSaveDTO;

public interface StaffContractSelectUseCase {

	StaffContactSaveDTO select(String companyCode, String staffNo);
}
