package com.like.hrm.staff.application.port.in.license;

public interface StaffLicenseDeleteUseCase {
	void delete(String companyCode, String staffNo, Long seq);
}
