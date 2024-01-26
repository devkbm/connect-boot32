package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.application.port.dto.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.application.port.dto.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.dto.StaffQueryConditionDTO;

public interface StaffQueryRepository {

	
	List<Staff> getStaffList(StaffQueryConditionDTO dto);
	
	ResponseStaffCurrentAppointment getStaffCurrentAppointment(String companyCode, String staffNo);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String companyCode, String staffNo);
	
	List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String companyCode, String staffNo);
	
}
