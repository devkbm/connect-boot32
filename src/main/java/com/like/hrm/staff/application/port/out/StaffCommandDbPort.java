package com.like.hrm.staff.application.port.out;

import com.like.hrm.staff.domain.model.Staff;

public interface StaffCommandDbPort {

	Staff select(String companyCode, String staffNo);
	
	void save(Staff entity);
	
	void delete(String companyCode, String staffNo);
}
