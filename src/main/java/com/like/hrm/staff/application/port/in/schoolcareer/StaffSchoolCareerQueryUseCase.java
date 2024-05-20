package com.like.hrm.staff.application.port.in.schoolcareer;

import java.util.List;

import com.like.hrm.staff.dto.StaffSchoolCareerSaveDTO;

public interface StaffSchoolCareerQueryUseCase {
	List<StaffSchoolCareerSaveDTO> select(String companyCode, String staffNo);
}
