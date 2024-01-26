package com.like.hrm.staff.application.port.in.appointment;

public interface StaffAppointmentApplyUseCase {
	void apply(String companyCode, String staffNo, Long seq);
}
