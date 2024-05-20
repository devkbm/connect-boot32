package com.like.hrm.staff.application.port.in.schoolcareer;

import com.like.hrm.staff.dto.StaffSchoolCareerSaveDTO;

public interface StaffSchoolCareerSelectUseCase {	
	StaffSchoolCareerSaveDTO select(String companyCode, String staffNo, Long seq);
}
