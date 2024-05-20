package com.like.hrm.staff.application.service.appointment;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.appointment.StaffAppointmentSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffAppointmentSelectDbPort;
import com.like.hrm.staff.dto.StaffAppointmentRecordDTO;

@Service
public class StaffAppointmentSelectService implements StaffAppointmentSelectUseCase {

	StaffAppointmentSelectDbPort dbPort;
	
	StaffAppointmentSelectService(StaffAppointmentSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public StaffAppointmentRecordDTO select(String companyCode, String staffNo, Long seq) {
		return StaffAppointmentRecordDTO.convert(this.dbPort.select(companyCode, staffNo, seq));
	}

	

}
