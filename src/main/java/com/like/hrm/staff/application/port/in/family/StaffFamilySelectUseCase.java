package com.like.hrm.staff.application.port.in.family;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;

public interface StaffFamilySelectUseCase {	
	StaffFamilySaveDTO select(String companyCode, String staffNo, Long seq);
}

