package com.like.hrm.staff.domain.model;

import java.util.List;

import com.like.hrm.staff.dto.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.dto.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.dto.StaffQueryConditionDTO;

public interface StaffQueryRepository {

	
	List<Staff> getStaffList(StaffQueryConditionDTO dto);
	
	ResponseStaffCurrentAppointment getStaffCurrentAppointment(String companyCode, String staffNo);
		
	List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String companyCode, String staffNo);
	
	List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String companyCode, String staffNo);
	
}
