package com.like.hrm.staff.application.service.appointment;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.appointment.StaffAppointmentApplyUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StaffAppointmentApplyService implements StaffAppointmentApplyUseCase {

	StaffCommandDbPort dbPort;
	
	StaffAppointmentApplyService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void apply(String companyCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(companyCode, staffNo)
						    .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원정보가 존재하지 않습니다."));
		
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, seq);
		
		staff.applyAppointmentRecord(entity);
	}

}
