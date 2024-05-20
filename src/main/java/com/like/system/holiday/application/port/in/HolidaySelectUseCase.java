package com.like.system.holiday.application.port.in;

import java.time.LocalDate;

import com.like.system.holiday.dto.HolidaySaveDTO;

public interface HolidaySelectUseCase {
	HolidaySaveDTO select(String companyCode, LocalDate date);
}
