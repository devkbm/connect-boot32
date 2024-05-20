package com.like.hrm.staff.application.port.in.license;

import java.util.List;

import com.like.hrm.staff.dto.StaffLicenseSaveDTO;

public interface StaffLicenseQueryUseCase {
	List<StaffLicenseSaveDTO> select(String companyCode, String staffNo);
}
